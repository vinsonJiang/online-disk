package io.vinson.web.svc.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;


/**
 * @author: jiangweixin
 * @date: 2019/5/5
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
    
    public static final String WEBSOCKET_PATH = "/ws";

    public static final int MAX_CONTENT_LENGTH = 65536;
    
    private final SslContext sslContext;

    public WebSocketServerInitializer(SslContext sslCtx) {
        sslContext = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if(sslContext != null) {
            pipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(MAX_CONTENT_LENGTH));
        pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
        pipeline.addLast(new WebSocketFrameHandler());
    }
}
