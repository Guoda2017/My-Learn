package com.guo.learn.io.rpc.consumer;

import com.guo.learn.io.rpc.proxy.RpcProxy;
import com.guo.learn.io.rpc.service.RpcHelloService;
import com.guo.learn.io.rpc.service.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 15:33
 * @Description :
 */
@Slf4j
public class RpcConsumer {

    public static void main(String[] args) {
        RpcHelloService helloService = RpcProxy.create(RpcHelloService.class);

        log.info(helloService.hello("Tom老师"));

        RpcService rpcService = RpcProxy.create(RpcService.class);

        log.info(String.valueOf(rpcService.add(8, 2)));
        log.info(String.valueOf(rpcService.sub(8, 2)));
        log.info(String.valueOf(rpcService.multiply(8, 2)));
        log.info(String.valueOf(rpcService.div(8, 2)));

    }

}
