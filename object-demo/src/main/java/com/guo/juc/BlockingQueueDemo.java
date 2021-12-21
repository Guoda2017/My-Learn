package com.guo.juc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: Blocking -> 阻塞 Queue -> 队列 提供了一系列方法做到线程实现自动的阻塞
 * 第一个就是offer对应的是原来的那个add,提供了poll取数据，然后提供了peek拿出来这个数据。
 * 那么这个是什么意思呢？我们读一下这个offer的概念 ，offer是往里头添加，加进去没加进去它会给你个布尔类型的返回值，
 *
 * 和原来的add是什么区别呢？add如果加不进去了是会抛异常的。
 *
 * 所以一般的情况下我们用的最多的Queue里面都用offer,它会给你一个返回值，peek的概念是去取并不是让你remove掉，poll是取并且remove掉，
 * 而且这几个对于BlockingQueue来说也确实是线程安全的一个操作。对于Queue经常用的接口就这么几个，大家了解就可以。
 * @author: guofengbo
 * @date: 2020-05-18 16:29
 **/
public class BlockingQueueDemo {

    public static void main(String[] args) {
        Queue<String> strings = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strings.offer("a" + i);
        }

        System.out.println(strings);

        System.out.println(strings.size());

        //出队列 并返回值
        System.out.println(strings.poll());
        System.out.println(strings.size());

        //不出队列 返回值
        System.out.println(strings.peek());
        System.out.println(strings.size());
    }

}
