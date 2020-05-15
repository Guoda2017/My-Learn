package com.guo.object;

import java.lang.reflect.Field;

/**
 * @description: 使用反射模拟GC之后的数据情况
 * @author: guofengbo
 * @date: 2020-05-11 16:18
 **/
public class ThreadLocalGCDemo {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            try {
                test("abc",false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> {
            try {
                test("def", true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t2.join();
    }

    private static void test(String s, boolean isGC) throws Exception {
        new ThreadLocal<>().set(s);
        if (isGC) {
            System.gc();
        }
        Thread t = Thread.currentThread();
        Class<? extends Thread> clz = t.getClass();
        Field field = clz.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object threadLocalMap = field.get(t);

        Class<?> tlmClass = threadLocalMap.getClass();
        Field tableField = tlmClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] arr = (Object[]) tableField.get(threadLocalMap);
        for (Object o : arr) {
            if (o != null) {
                Class<?> entryClass = o.getClass();
                Field valueField = entryClass.getDeclaredField("value");
                Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                valueField.setAccessible(true);
                referenceField.setAccessible(true);
                System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
            }
        }
    }

}
