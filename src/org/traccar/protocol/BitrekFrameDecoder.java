package org.traccar.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.traccar.BaseFrameDecoder;

public class BitrekFrameDecoder extends BaseFrameDecoder {

    private static final int MESSAGE_MINIMUM_LENGTH = 12;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ByteBuf buf) throws Exception {
        // Check minimum length
        if (buf.readableBytes() < MESSAGE_MINIMUM_LENGTH) {
            return null;
        }

        // Read packet
        int length = buf.getUnsignedShort(buf.readerIndex());
        int read = buf.readableBytes();
        if (length > 0) {
            /*if (buf.readableBytes() >= (length + 2)) {
                return buf.readRetainedSlice(length + 2);
            }*/
            return buf.readRetainedSlice(read);
        } else {
            int dataLength = buf.getInt(buf.readerIndex() + 4);
            if (buf.readableBytes() >= (dataLength + 12)) {
                return buf.readRetainedSlice(dataLength + 12);
            }
        }

        return null;
    }
}
