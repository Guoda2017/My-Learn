package com.guo.object;

/**
 * @description:
 * @author: guofengbo
 * @date: 2020-05-12 10:12
 **/
public enum EnumDemo {

    SPRING() {
        @Override
        public EnumDemo getNextSeason() {
            return null;
        }
    };

    public abstract EnumDemo getNextSeason();
}
