package com.guo.singleton;

/**
 * @description: DCL单例模式
 * @author: guofengbo
 * @date: 2020-04-18 21:59
 **/
public class DCLSingleton {

    private static volatile DCLSingleton INSTANCE;

    private DCLSingleton() {

    }

    private static DCLSingleton getDCLSingleton() {
        if (INSTANCE == null) {
            synchronized (DCLSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DCLSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
