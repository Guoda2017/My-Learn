package java.io.service;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author : fengbo.guo
 * @date : 2021-12-22 16:23
 * @Description :
 */
@Slf4j
public class AioClient {

    private AsynchronousSocketChannel client;

    public AioClient() throws IOException {
        client = AsynchronousSocketChannel.open();
    }

    public static void main(String[] args) throws IOException {
        new AioClient().connect("localhost", 8000);
    }

    public void connect(String host, int port) {
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Object>() {

            @Override
            public void completed(Void result, Object attachment) {
                try {
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    log.info("已发送至服务器");
                } catch (Exception e) {
                    log.error("错误信息:", e);
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        final ByteBuffer bb = ByteBuffer.allocate(1024);
        client.read(bb, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                log.info("I/O操作完成");
                log.info("获取反馈结果{}", bb.array());
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            log.error("错误信息:", e);
        }
    }
}
