package com.guo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-02-29 19:54
 **/
@Service
@DependsOn("test1")
public class Test2 {

    public Test2(Test1 test1){

    }
}
