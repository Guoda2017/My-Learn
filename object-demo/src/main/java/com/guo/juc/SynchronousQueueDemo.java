package com.guo.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Description: SychronousQueue 容量为0 这个东西不是用来装内容的
 * Synchronous是专门用来两个线程之间传内容的,给线程下达任务的,和之前讲过的Exchanger容器的概念一致
 * @author: guofengbo
 * @date: 2020-05-25 17:51
 **/
public class SynchronousQueueDemo { //容量为0

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strings = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //阻塞等待消费者消费
        //strings.put("aaa");
        strings.put("bbb");
        //strings.add("aaa");
        System.out.println(strings.size());
    }

}
