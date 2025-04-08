package java.io.rpc.proxy;

import java.io.rpc.protocol.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 15:05
 * @Description :
 */
@Slf4j
public class RpcProxy {

    public static <T> T create(Class<?> clazz) {
        // clazz传进来本身就是 interface
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface() ? new Class[]{clazz} : clazz.getInterfaces();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, proxy);
    }

    private static class MethodProxy implements InvocationHandler {

        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            //如果传进来是一个以实现的具体类
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return rpcInvoke(proxy, method, args);
            }
            return null;
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {

            //传输协议封装
            InvokerProtocol msg = new InvokerProtocol();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setParams(method.getParameterTypes());
            msg.setValues(args);

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            NioEventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();

                                //自定义协议解码器
                                pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                //自定义协议编码器
                                pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
                                //对象参数类型编码器
                                pipeline.addLast("encoder", new ObjectEncoder());
                                //对象参数类型解码器
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                //自定义注册器
                                pipeline.addLast("handler", consumerHandler);
                            }
                        });
                ChannelFuture future = b.connect("127.0.0.1", 8080).sync();
                future.channel().writeAndFlush(msg).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
            return consumerHandler.getResponse();
        }
    }

}
