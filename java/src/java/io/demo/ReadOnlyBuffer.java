package java.io.demo;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author : fengbo.guo
 * @date : 2021-12-22 19:20
 * @Description :
 */
@Slf4j
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //缓冲区中的数据0~9
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        //改变源缓冲区的内容
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(buffer.capacity());

        while (readOnlyBuffer.remaining() > 0) {
            log.info("get:{}", readOnlyBuffer.get());
        }
    }

}
