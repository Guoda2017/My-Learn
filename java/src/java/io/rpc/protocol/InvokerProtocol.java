package java.io.rpc.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 13:49
 * @Description : 自定义协议
 */
@Data
@Slf4j
public class InvokerProtocol implements Serializable {

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] params;

    /**
     * 参数列表
     */
    private Object[] values;

}
