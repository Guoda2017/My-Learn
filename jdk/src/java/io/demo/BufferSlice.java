package java.io.demo;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author : fengbo.guo
 * @date : 2021-12-22 18:33
 * @Description : 缓冲区分片
 */
@Slf4j
public class BufferSlice {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        //缓冲区中的数据0~9
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        //改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            log.info("get:{}", buffer.get());
        }


    }

}
