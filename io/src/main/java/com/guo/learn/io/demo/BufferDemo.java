package com.guo.learn.io.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fengbo.guo
 * @date : 2021-12-22 18:15
 * @Description :
 */
@Slf4j
public class BufferDemo {

    public static void main(String[] args) throws Exception {

        FileInputStream fin = new FileInputStream("C://Users//fengbo.guo//Desktop//123.txt");

        //创建文件的操作管道
        FileChannel fc = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化buffer", buffer);

        fc.read(buffer);
        output("读取buffer", buffer);

        //锁定操作范围
        buffer.flip();
        output("调用flip", buffer);

        //判断有没有可读数据
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            log.info("读取到的数据:{}", String.valueOf(b));
        }
        output("调用get", buffer);

        //解锁
        buffer.clear();
        output("解锁", buffer);

        fin.close();
    }

    public static void output(String step, Buffer buffer) {
        log.info("{}:capacity:{},position:{},limit:{}", step, buffer.capacity(), buffer.position(), buffer.limit());
    }
}
