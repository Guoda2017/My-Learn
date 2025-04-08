package design.简单工厂模式;

/**
 * @description: 启动类
 * @author: guofengbo
 * @date: 2020-06-16 20:47
 **/
public class Client {

    public static void main(String[] args) {
        Product product = Factory.getProduct("A");
        product.methodDiff();
        product.methodSame();
    }
}
