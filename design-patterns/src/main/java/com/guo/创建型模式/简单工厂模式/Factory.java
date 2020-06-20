package com.guo.创建型模式.简单工厂模式;

/**
 * @Description: 工厂类
 * @author: guofengbo
 * @date: 2020-06-16 20:46
 **/
public class Factory {

    //静态工厂方法
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
            //初始化设置product
        } else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
            //初始化设置product
        }
        return product;
    }
}
