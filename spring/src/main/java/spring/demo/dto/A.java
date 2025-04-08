package main.java.spring.demo.dto;

/**
 * @description: A
 * @author: guofengbo
 * @date: 2020-05-08 10:55
 **/
public class A {

    /**
     * 模拟循环依赖
     */
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
