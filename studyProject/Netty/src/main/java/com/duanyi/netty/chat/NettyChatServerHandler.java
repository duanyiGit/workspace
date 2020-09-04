package com.duanyi.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NettyChatServerHandler extends SimpleChannelInboundHandler {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 当客户端连接服务器完成就会触发该方法
     *
     * @param handlerContext
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext handlerContext) throws Exception {
        Channel channel = handlerContext.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"上线了"+sdf.format(new Date()));
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress()+"上线了");
    }

    /**
     * 连接断开
     * @param handlerContext
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext handlerContext) throws Exception {
        Channel channel = handlerContext.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"下线了"+sdf.format(new Date()));
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress()+"下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext handlerContext, Object msg) throws Exception {
        Channel channel = handlerContext.channel();
        channelGroup.forEach(ch -> {
                    if(ch != channel){
                        ch.writeAndFlush("[客户端]:"+channel.remoteAddress()+"发送了消息："+msg);
                    }else{
                        ch.writeAndFlush("[自己]发送了消息："+msg);
                    }
                }
         );

    }


    /**
     * 处理异常, 一般是需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
