package com.tianen.chen.push.service;

import com.tianen.chen.push.service.send.DispatcherImpl;
import com.tianen.chen.push.service.source.Consumer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    WebSocketChannelInitializer(){
        new Consumer(channelGroup).start();
    }
    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8888));
        pipeline.addLast(new WebSocketServerProtocolHandler("/te"));
        pipeline.addLast(new PushSocketHandler(channelGroup,new DispatcherImpl()));
    }
}
