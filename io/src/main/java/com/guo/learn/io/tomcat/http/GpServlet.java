package com.guo.learn.io.tomcat.http;

import com.guo.learn.io.tomcat.req.GpReq;
import com.guo.learn.io.tomcat.resp.GpResp;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:18
 * @Description :
 */
@Slf4j
public abstract class GpServlet {

    public void service(GpReq gpReq, GpResp gpResp) throws IOException {

        if ("GET".equalsIgnoreCase(gpReq.getMethod())) {
            doGet(gpReq, gpResp);
        } else {
            doPost(gpReq, gpResp);
        }

    }

    protected abstract void doGet(GpReq gpReq, GpResp gpResp) throws IOException;

    protected abstract void doPost(GpReq gpReq, GpResp gpResp) throws IOException;



}
