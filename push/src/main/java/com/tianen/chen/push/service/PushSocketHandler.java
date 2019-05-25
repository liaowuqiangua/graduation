package com.tianen.chen.push.service;

import com.tianen.chen.base.pojo.Access;
import com.tianen.chen.push.service.dao.MongoRequest;
import com.tianen.chen.push.service.send.Dispatcher;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // 采用单个channelGroup  对服务器压力有点大 可以优化为多个channelGroup
    private final ChannelGroup channelGroup;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private Dispatcher<String> sender;
    PushSocketHandler(ChannelGroup channelGroup, Dispatcher<String> sender){
        this.channelGroup = channelGroup;
        this.sender = sender;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        log.info("receive msg from {} ,msg :[{}]",ctx.channel().localAddress(),msg.text());
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("{\"msg\":\"success\",\"time\":\""+System.currentTimeMillis()+"\"}"));
        // 应付一下
        new MongoRequest("access").saveAccessInfo(new Access(ctx.channel().remoteAddress().toString(),"",msg.text(),System.currentTimeMillis()));
        ctx.channel().writeAndFlush(new TextWebSocketFrame(sender.dispatch(msg.text())));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        channelGroup.add(ctx.channel());
        log.info(" handler added ! from {}",ctx.channel().localAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        log.info(" handler removed ! from {}",ctx.channel().localAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.channel().close();
        log.error("exception context {} , msg {}",ctx.name(),cause.getMessage());
    }
}
