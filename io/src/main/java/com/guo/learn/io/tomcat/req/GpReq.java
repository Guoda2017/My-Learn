package com.guo.learn.io.tomcat.req;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:23
 * @Description :
 */
@Slf4j
@Data
public class GpReq {

    private String method;
    private String url;

    public GpReq(InputStream in) {
        try {
            //获取 http 内容
            String content = "";

            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = in.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }
            log.info("读取到的buff字符串:{}", content);

            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
            log.info("method:{},url:{}", method, url);

        } catch (IOException e) {
            log.error("错误信息:", e);
        }

    }

}
