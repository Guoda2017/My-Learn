package com.guo.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ThreadLocal Demo
 * @author: guofengbo
 * @date: 2020-05-11 14:05
 **/
public class ThreadLocalDemo {

    private List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalDemo> holder = ThreadLocal.withInitial(ThreadLocalDemo::new);

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size:" + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalDemo.add("测试ThreadLocal");
        System.out.println(holder.get().messages);
        ThreadLocalDemo.clear();
    }
}
