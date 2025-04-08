package main.java.spring.demo.dto;

/**
 * @description: B
 * @author: guofengbo
 * @date: 2020-05-08 10:55
 **/
public class B {

    /**
     * 模拟循环依赖
     */
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
