package java.io.rpc.registry;

import java.io.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 14:22
 * @Description : 注册器
 */
@Slf4j
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    /**
     * 保留所有可用的服务
     */
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();

    /**
     * 保存所有相关的服务类
     */
    public List<String> classNameList = new ArrayList<>();

    public RegistryHandler() {
        //完成递归扫描
        scannerClass("com.guo.learn.io.rpc.provider");
        //执行注册
        doRegister();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;

        //当客户端建立连接时,需要从自定义协议中获取信息,以及具体的服务和实参
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParams());
            result = method.invoke(clazz, request.getValues());
        }

        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));

        if (Objects.isNull(url)) {
            return;
        }

        File dir = new File(url.getFile());

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            //如果是一个文件夹 继续递归
            if (file.isDirectory()) {
                scannerClass(packageName + "," + file.getName());
            } else {
                classNameList.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    private void doRegister() {
        if (classNameList.size() == 0) {
            return;
        }

        for (String className : classNameList) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());
            } catch (Exception e) {
                log.error("注册失败:", e);
            }
        }

    }

}
