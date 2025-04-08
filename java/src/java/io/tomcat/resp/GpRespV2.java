package java.io.tomcat.resp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 14:23
 * @Description :
 */
@Slf4j
public class GpRespV2 {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public GpRespV2(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String out) throws Exception {
        try {
            if (out == null || out.length() == 0) {
                return;
            }

            //设置http及请求头信息
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8)));

            ctx.write(response);

        } finally {
            ctx.flush();
            ctx.close();
        }
    }

}
