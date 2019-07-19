package com.lxl.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

	private Object response;

	public Object getResponse() {
		return response;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		response = msg;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("client exception is general");
	}
	// 介绍一下这些回调方法被触发的时机
	// channelRegistered 当前channel注册到EventLoop true true
	// channelUnregistered 当前channel从EventLoop取消注册 true true
	// channelActive 当前channel激活的时候 true true
	// channelInactive 当前channel不活跃的时候，也就是当前channel到了它生命周期末 true true
	// channelRead 当前channel从远端读取到数据 true true
	// channelReadComplete channel read消费完读取的数据的时候被触发 true true
	// userEventTriggered 用户事件触发的时候
	// channelWritabilityChanged channel的写状态变化的时候触发
}
