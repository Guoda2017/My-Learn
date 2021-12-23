package com.guo.learn.io.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 11:05
 * @Description :
 */
@Slf4j
public class SelectorDemo {

    private Selector getSelector() throws IOException {

        Selector sel = Selector.open();

        //创建通道 可选择 非阻塞
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        //绑定通道到端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(8000);
        socket.bind(address);

        //注册时间
        server.register(sel, SelectionKey.OP_ACCEPT);

        SelectionKey key = null;


        return sel;
    }

}
