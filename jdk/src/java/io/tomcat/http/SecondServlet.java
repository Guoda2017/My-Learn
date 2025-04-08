package java.io.tomcat.http;

import java.io.tomcat.req.GpReq;
import java.io.tomcat.resp.GpResp;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:27
 * @Description :
 */
@Slf4j
public class SecondServlet extends GpServlet {

    @Override
    protected void doGet(GpReq gpReq, GpResp gpResp) throws IOException {
        this.doPost(gpReq, gpResp);
    }

    @Override
    protected void doPost(GpReq gpReq, GpResp gpResp) throws IOException {
        gpResp.write("this is second servlet");
    }
}
