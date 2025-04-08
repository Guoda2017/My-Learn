package lock.guo.juc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 用链表实现的BlockingQueue 是一个无界队列(就是可以一直装到你的内存满了为止)
 * @author: guofengbo
 * @date: 2020-05-18 12:39
 **/
public class LinkedBlockingQueueDemo {

    private static BlockingQueue<String> strings = new LinkedBlockingQueue<>();

    private static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strings.put("a" + i);//如果满了就会等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    //如果空了 就会等待
                    try {
                        System.out.println(Thread.currentThread().getName() + "take - " + strings.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c1").start();
        }
        System.out.println("主线程结尾-------");
    }
}
