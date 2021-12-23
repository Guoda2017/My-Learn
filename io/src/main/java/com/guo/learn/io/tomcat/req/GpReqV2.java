package com.guo.learn.io.tomcat.req;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:23
 * @Description :
 */
@Slf4j
@Data
public class GpReqV2 {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GpReqV2(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
    }

    public String getUrl() {
        return req.uri();
    }

    public String getMethod() {
        return req.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> parameters = getParameters();
        List<String> param = parameters.get(name);
        if (null == param) {
            return null;
        }
        return param.get(0);
    }
}
