package com.guo;

import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 20:02
 **/
public class Start {

    public static void main(String[] args) {
       int i = 0;

        for (int j = 0; j < 5; j++) {
            i++;
        }
        System.out.println(i);
    }

}
