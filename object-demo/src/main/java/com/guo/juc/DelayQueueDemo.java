package com.guo.juc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description: DelayQueue可以实现在时间上的排序, 这个DelayQueue能实现按照在里面等待的时间来进行排序
 * @author: guofengbo
 * @date: 2020-05-24 22:12
 **/
public class DelayQueueDemo {

    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {

        String name;
        long runningTime;

        MyTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
