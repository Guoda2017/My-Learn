package java.io.tomcat;

import java.io.tomcat.http.GpServlet;
import java.io.tomcat.req.GpReq;
import java.io.tomcat.resp.GpResp;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:46
 * @Description :
 */
@Slf4j
public class GpTomcat {

    private int port = 8080;
    private ServerSocket server;
    private Map<String, GpServlet> servletMapping = new HashMap<>();

    private Properties webxml = new Properties();

    public static void main(String[] args) {
        new GpTomcat().start();
    }

    public void start() {
        // 加载配置文件 初始化 servletMapping
        init();

        try {
            server = new ServerSocket(this.port);
            log.info("GpTomcat 已启动 监听的端口是:{}", this.port);

            //等到用户请求 用一个死循环来等待用户请求
            while (true) {
                Socket client = server.accept();
                // http请求 发送的数据就是字符串--有规律的字符串(http)
                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                GpReq req = new GpReq(is);
                GpResp resp = new GpResp(os);

                //从协议中获取URL 把相应的Servlet用反射进行实例化
                String url = req.getUrl();

                if (servletMapping.containsKey(url)) {
                    //调用实例化对象的service方法
                    servletMapping.get(url).service(req, resp);
                } else {
                    resp.write("404 - Not Found");
                }

                os.flush();
                os.close();

                is.close();
                client.close();

            }

        } catch (Exception e) {
            log.info("错误信息:", e);
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
