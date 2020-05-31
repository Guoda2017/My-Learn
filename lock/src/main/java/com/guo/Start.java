package com.guo;

import java.util.Optional;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 20:02
 **/
public class Start {

    public static void main(String[] args) {
        Test test = null;

        System.out.println(Optional.ofNullable(test).isPresent());
    }

}
