package com.guo.learn.io.service;


import lombok.extern.slf4j.Slf4j;

import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class AioService {

    private final int port;

    public static void main(String[] args) {
        int port = 8000;
        new AioService(port);
    }

    public AioService(int port) {
        this.port = port;
        listen();
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);

        } catch (Exception e) {
            log.error("错误信息:",e);
        }
        
    }
}
