package org.luckystars.weixin.transfer;

import java.io.OutputStream;

import org.luckystars.weixin.transfer.msg.Msg;
import org.luckystars.weixin.transfer.msg.MsgFactory;


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
	
	public static void putContext(HandlerContext ctx){
		context.set(ctx);
	}
	
	public static void cleanContext(){
		context.set(null);
	}
	
	public Msg getMsg() {
		return msg;
	}

	public OutputStream getReplyStream() {
		return out;
	}
	
	
	
	
}
