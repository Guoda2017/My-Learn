package lock.guo.juc;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 循环屏障
 *
 * 一组线程相互等待,知道所有线程到达统一同步点
 * 类似一群人困到了一个栅栏前面,只有所有人都到达这个栅栏之后,才能合力突破栅栏
 *
 * @author: guofengbo
 * @date: 2020-05-08 13:48
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("等裁判吹口哨...");
                    Thread.sleep(2000);
                    System.out.println("裁判吹口哨->>>>>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Runner runner1 = new Runner(barrier, "张三");
        Runner runner2 = new Runner(barrier, "李四");
        Runner runner3 = new Runner(barrier, "王五");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(runner1);
        service.execute(runner2);
        service.execute(runner3);

        service.shutdown();
    }

    static class Runner implements Runnable {

        private CyclicBarrier cyclicBarrier;
        private String name;

        public Runner() {

        }

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                //模拟准备耗时
                int i = new Random().nextInt(5000);
                System.out.println(i);
                Thread.sleep(i);

                System.out.println(name + ": 准备OK");
                cyclicBarrier.await();
                System.out.println(name + ": 开跑");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
