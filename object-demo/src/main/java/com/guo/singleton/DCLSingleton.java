package com.guo.singleton;

/**
 * @description: DCL单例模式
 * @author: guofengbo
 * @date: 2020-04-18 21:59
 **/
public class DCLSingleton {

    private static volatile DCLSingleton DCL_SINGLETON;

    private DCLSingleton() {

    }

    private static DCLSingleton instance() {
        if (DCL_SINGLETON == null) {
            synchronized (DCLSingleton.class) {
                if (DCL_SINGLETON == null) {
                    DCL_SINGLETON = new DCLSingleton();
                }
            }
        }
        return DCL_SINGLETON;
    }
}
