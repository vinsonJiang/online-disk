package io.vinson.file.service.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by jiangweixin on 2018/5/17.
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();

        p.addLast(new HttpServerCodec());

        // add gizp compressor for http response content
        p.addLast(new HttpContentCompressor());

        p.addLast(new HttpObjectAggregator(1048576));

        p.addLast(new ChunkedWriteHandler());

        p.addLast(new NettyServerHandler());
    }
}
