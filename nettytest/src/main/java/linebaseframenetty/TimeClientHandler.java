package linebaseframenetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author hlf
 * @since 2019/07/28 16:02
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message;
        for(int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes( req );
            ctx.writeAndFlush( message );
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        String body = (String) msg;
        System.out.println("NOW is:" + body + ";the counter is:" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
