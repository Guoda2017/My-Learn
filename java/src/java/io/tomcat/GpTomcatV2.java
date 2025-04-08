package java.io.tomcat;

import java.io.tomcat.handler.GpTomcatHandler;
import java.io.tomcat.http.GpServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:46
 * @Description :
 */
@Slf4j
public class GpTomcatV2 {

    private int port = 8080;
    private ServerSocket server;
    private Map<String, GpServlet> servletMapping = new HashMap<>();

    private Properties webxml = new Properties();

    public static void main(String[] args) {
        new GpTomcatV2().start();
    }

    public void start() {
        // 加载配置文件 初始化 servletMapping
        init();

        //netty封装了 NIO 的 reactor 模型 Boss Worker
        //boss线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            //创建对象
            ServerBootstrap server = new ServerBootstrap();

            //配置参数
            server.group(bossGroup, workerGroup)
                    //主线程处理类 看到这样的写法 底层用的就是反射
                    .channel(NioServerSocketChannel.class)
                    //子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //客户端初始化处理
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {

                            //无锁化串行线程
                            //解码器
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpResponseDecoder());
                            //业务逻辑
                            client.pipeline().addLast(new GpTomcatHandler());
                        }
                    })
                    //针对主线程配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //启动服务器
            ChannelFuture f = server.bind(port).sync();
            log.info("GpTomcat 已启动 监听的端口是:{}", this.port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.info("错误信息:", e);
        } finally {
            //关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private void init() {
        try {
            //加载 web.xml文件 同时初始化servletMapping对象
            String wenInf = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(wenInf + "web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    //单实例 多线程
                    GpServlet obj = (GpServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }

        } catch (Exception e) {
            log.error("错误信息:", e);
        }
    }

}
