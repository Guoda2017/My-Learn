package com.guo.learn.io.demo;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * @author : fengbo.guo
 * @date : 2021-12-22 17:22
 * @Description :
 */
@Slf4j
public class IntBufferDemo {

    public static void main(String[] args) {
        //分配新的int缓冲区 参数为缓冲区容量
        //新缓存区的当前位置将为0 其界限(限制位置)为其容量 它具有一个底层实现数组 其数组偏移量为0

        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            //将给定整数写入此缓冲区的当前位置 当前位置递增
            log.info("before limit:{}", buffer.limit());
            log.info("after position:{}", buffer.position());
            buffer.put(j);
            log.info("after limit:{}", buffer.limit());
            log.info("after position:{}", buffer.position());
        }

        //重设此缓冲区 将限制位置设置为当前位置 然后将当前位置设置为0
        buffer.flip();
        log.info("---------------------------------------------");
        //查看当前位置和限制位置之间是否有元素
        while (buffer.hasRemaining()) {
            //读取当前位置并递增位置
            log.info("before limit:{}", buffer.limit());
            log.info("after position:{}", buffer.position());
            log.info("{} --> ", buffer.get());
            log.info("after limit:{}", buffer.limit());
            log.info("after position:{}", buffer.position());
        }

    }

}
