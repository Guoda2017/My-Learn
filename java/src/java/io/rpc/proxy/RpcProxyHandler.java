package java.io.rpc.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 15:14
 * @Description :
 */
@Slf4j
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    public Object getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        log.info("proxy handler work");
        response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("client exception is general");
    }

}
