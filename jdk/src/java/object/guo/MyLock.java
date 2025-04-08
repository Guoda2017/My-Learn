package object.guo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description: \
 * @author: guofengbo
 * @create: 2020-01-09 20:35
 **/
public class MyLock implements Lock {

    //自定义实现AQS模板 实现tryAcquire 和 tryRelease
    private class Helper extends AbstractQueuedSynchronizer {

        //尝试独占获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            //getState() == 0的时候说明线程没有被占用
            if (getState() == 0) {
                //利用CAS原理修改state
                if (compareAndSetState(0, arg)) {
                    //设置当前现成占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            throw new UnsupportedOperationException();
        }

        //尝试独占释放锁
        @Override
        protected boolean tryRelease(int arg) {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
