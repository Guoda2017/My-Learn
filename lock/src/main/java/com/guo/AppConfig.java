package com.guo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 19:49
 **/
@ComponentScan("com.guo")
@Configuration
public class AppConfig {

    public static void main(String[] args) {
        System.out.println("-----");
    }

}
