package com.guo.创建型模式.简单工厂模式;

/**
 * @Description:
 *  简单工厂模式(Simple Factory Pattern)：定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类。
 *  因为在简单工厂模式中用于创建实例的方法是静态(static)方法，因此简单工厂模式又被称为静态工厂方法(Static Factory Method)模式，它属于类创建型模式。
 * @author: guofengbo
 * @date: 2020-06-16 20:44
 **/
public abstract class Product {

    //所有产品类的公共业务方法
    public void methodSame() {
        //公共的实现方法
    }

    //声明抽象业务方法
    public abstract void methodDiff();
}
