package java.io.tomcat.resp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:23
 * @Description :
 */
@Slf4j
public class GpResp {

    private OutputStream out;

    public GpResp(OutputStream out) {
        this.out = out;
    }

    public void write(String msg) throws IOException {
        //输出也要遵循 http
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n").append("Content-Type: text/html;\n").append("\r\n").append(msg);
        out.write(sb.toString().getBytes());
    }

}
