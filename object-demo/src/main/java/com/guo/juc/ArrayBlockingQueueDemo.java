package com.guo.juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ArrayBlockingQueue是有界的 你可以指定它一个固定的值10 它容器就是10 那么当你往里面扔数据的时候
 *               一旦他满了这个put方法就会阻塞住 然后可以用add方法满了之后会报异常
 * @author: guofengbo
 * @date: 2020-05-26 16:34
 **/
public class ArrayBlockingQueueDemo {

    private static BlockingQueue<String> strings = new ArrayBlockingQueue<>(10);

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strings.put("a" + i);
        }

        // 满了就会阻塞
        //strings.put("aaa");

        // 满了就会抛异常
        //strings.add("aaa");

        //会返回来判断加没加成功
        System.out.println(strings.offer("aaa", 1, TimeUnit.SECONDS));

        System.out.println(strings);
    }

}
