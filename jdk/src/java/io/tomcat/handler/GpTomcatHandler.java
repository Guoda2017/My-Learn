package java.io.tomcat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2021-12-23 16:23
 * @Description :
 */
@Slf4j
public class GpTomcatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpRequest) {
            log.info("hello");

            HttpRequest req = (HttpRequest) msg;

            //转交给我们自己的request实现


        }

    }

}
