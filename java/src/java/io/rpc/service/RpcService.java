package java.io.rpc.service;


/**
 * @author : fengbo.guo
 * @date : 2021-12-27 13:45
 * @Description :
 */
public interface RpcService {

    /**
     * 加
     */
    int add(int a, int b);

    /**
     * 减
     */
    int sub(int a, int b);

    /**
     * 乘
     */
    int multiply(int a, int b);

    /**
     * 除
     */
    int div(int a, int b);
}
