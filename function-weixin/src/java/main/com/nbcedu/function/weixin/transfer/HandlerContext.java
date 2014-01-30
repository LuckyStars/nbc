package com.nbcedu.function.weixin.transfer;

import java.io.OutputStream;

import com.nbcedu.function.weixin.transfer.msg.Msg;
import com.nbcedu.function.weixin.transfer.msg.MsgFactory;

public class HandlerContext {
	
	private static final ThreadLocal<HandlerContext> context = new ThreadLocal<HandlerContext>();
	
	protected Msg msg;
	
	/**
	 * 用于响应消息的流
	 */
	protected OutputStream out;
	
	public HandlerContext(String rawContent,OutputStream replyStream){
		this.msg = MsgFactory.build(rawContent);
		this.out = replyStream;
	}
	
	
	public static HandlerContext getContext(){
		return context.get();
	}
	
	public Msg getMsg() {
		return msg;
	}

	public OutputStream getReplyStream() {
		return out;
	}
	
	
}
