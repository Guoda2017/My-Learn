package com.guo.learn.io.rpc.service.impl;

import com.guo.learn.io.rpc.service.RpcHelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 13:50
 * @Description :
 */
@Slf4j
public class RpcHelloServiceImpl implements RpcHelloService {

    @Override
    public String hello(String name) {
        return "Hello" + name + "!";
    }
}
