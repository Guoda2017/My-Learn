package com.guo.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 可重入读写锁   可重入锁 : 读锁|写锁
 *  没有写锁的情况下 读锁允许多个线程同时访问 而写锁是独占的
 *  公平性: 支持公平锁和非公平锁 默认非公平锁 非公平锁比公平锁有更高的吞吐量
 *  可重入: 写锁可以获得读锁 读锁不能获得写锁.
 *  锁降级: 在获得写锁的情况下在获得读锁 然后释放写锁称为锁降级 反之称为锁升级 允许写锁降低为读锁 反之则不允许
 * @author: guofengbo
 * @date: 2020-07-02 19:54
 **/
public class ReentrantReadWriteLockDemo {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock readLock = rwLock.readLock();
    static Lock writeLock = rwLock.writeLock();

    //获取一个key对应的value
    public static final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    //设置key对应的value值
    public static final Object put(String key, Object value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    //清空所用内容
    public static final void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 锁降级示例
     */
    static class LockDec {

        static Map<String, Object> map = new HashMap<>();
        static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        static Lock readLock = rwLock.readLock();
        static Lock writeLock = rwLock.writeLock();
        public volatile boolean update = false;

        public void processData() {
            readLock.lock();
            if (!update) {
                //必须先释放读锁
                readLock.unlock();
                //锁降级从写锁获取到开始
                writeLock.lock();
                try {
                    if (!update) {
                        //准备数据流
                        update = true;
                    }
                    readLock.lock();
                } finally {
                    writeLock.unlock();
                }
                //锁降级完成 写锁->读锁
            }
            try {
                //使用数据流程
            } finally {
                readLock.unlock();
            }
        }
    }

}
