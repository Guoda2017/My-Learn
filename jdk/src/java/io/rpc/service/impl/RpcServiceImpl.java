package java.io.rpc.service.impl;

import java.io.rpc.service.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 13:51
 * @Description :
 */
@Slf4j
public class RpcServiceImpl implements RpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
