package lock.guo.juc;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @Description: TransferQueue可以给线程来传递任务 不像SynchronousQueue只能传递一个
 * TransferQueue只能传递一个 TransferQueue做出列表可以传好多个
 *  如果我们用put就相当于一个线程来了往里面装就可以离开了
 * transfer就是装完在这等着,组设等有人把它取走这个线程才回去干自己的事情
 * @author: guofengbo
 * @date: 2020-05-24 22:11
 **/
public class TransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.transfer("aaa");

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
