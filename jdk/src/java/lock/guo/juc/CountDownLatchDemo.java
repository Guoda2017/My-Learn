package lock.guo.juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @description: CountDownLatch DEMO
 *
 * 同步辅助器,允许一个或多个线程一直等待,直到一组在其他线程执行的操作全部完成
 *
 * @author: guofengbo
 * @date: 2020-05-08 12:26
 **/
public class CountDownLatchDemo {

    private static SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {

        //想要几个线程均完成之后 再进行后续操作则放多少个线程
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Worker w1 = new Worker("张三", 2000, countDownLatch);
        Worker w2 = new Worker("李四", 5000, countDownLatch);
        //Worker w3 = new Worker("王五", 3000, countDownLatch);

        w1.start();
        w2.start();
        //w3.start();

        long startTime = System.currentTimeMillis();
        countDownLatch.await();
        System.out.println("bug全部解决，领导可以给客户交差了，任务总耗时：" + (System.currentTimeMillis() - startTime));

    }

    static class Worker extends Thread {

        String name;
        int workTime;
        CountDownLatch latch;

        public Worker() {

        }

        Worker(String name, int workTime, CountDownLatch latch) {
            this.name = name;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(name + "开始修复bug,当前时间:" + simpledateformat.format(new Date()));
            doWork();
            System.out.println(name + "结束修复bug,当前时间:" + simpledateformat.format(new Date()));
            latch.countDown();
        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
