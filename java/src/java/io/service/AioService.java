package java.io.service;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class AioService {

    private final int port;

    public AioService(int port) {
        this.port = port;
        listen();
    }

    public static void main(String[] args) {
        int port = 8000;
        new AioService(port);
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            log.info("服务已启动,监听端口:{}", port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    log.info("I/O操作成功,开始获取数据");

                    try {
                        buffer.clear();
                        result.read(buffer).get();
                    } catch (Exception e) {
                        log.error("I/O操作异常:", e);
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (IOException e) {
                            log.error("I/O操作异常:", e);
                        }
                    }

                    log.info("I/O操作完成");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    log.info("I/O操作失败");
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                log.error("错误信息:", e);
            }

        } catch (Exception e) {
            log.error("错误信息:", e);
        }

    }
}
