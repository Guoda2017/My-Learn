package com.guo.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: SemaphoreDemo
 *
 * 信号量,用来控制同一时间,资源可被访问的线程数量,一般可用于流量的控制
 *
 * @author: guofengbo
 * @date: 2020-05-08 15:54
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        int count = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        //指定最多只有五个线程同时执行
        Semaphore semaphore = new Semaphore(5);

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            final int no = i;
            executorService.execute(() -> {
                try {
                    //获得许可
                    semaphore.acquire();
                    System.out.println(no + ":号车可通行");
                    //模拟车辆通行耗时
                    Thread.sleep(random.nextInt(2000));
                    //释放许可
                    semaphore.release();
                    System.out.println(no + ":号车已通行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
