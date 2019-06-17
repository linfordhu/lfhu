import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * discard netty demo
 * @author hlf
 * @since 2019/06/17 09:07
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) {
        //Discard the received data silently.
        ( ( ByteBuf ) msg ).release();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
