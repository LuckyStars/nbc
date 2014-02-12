package org.luckystars.weixin.framework;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.WeixinView;

public class HandlerContext {
	
	private static final Logger logger = Logger.getLogger(HandlerContext.class);
	
	private static final ThreadLocal<HandlerContext> context = new ThreadLocal<HandlerContext>();
	
	protected IncomeMessage msg;
	
	/**
	 * 用于响应消息的流
	 */
	protected OutputStream out;
	
	public HandlerContext(IncomeMessage msg,OutputStream replyStream){
		this.msg = msg;
		this.out = replyStream;
	}
	
	public static HandlerContext getContext(){
		return context.get();
	}
	
	/**
	 * 把ctx放入ThreadLocal
	 * @param ctx
	 * @author xuechong
	 */
	public static void putContext(HandlerContext ctx){
		context.set(ctx);
	}
	/**
	 * 释放当前ThreadLocal的context
	 * @author xuechong
	 */
	public static void cleanContext(){
		context.set(null);
	}
	
	public IncomeMessage getMsg() {
		return msg;
	}

	public OutputStream getReplyStream() {
		return out;
	}
	
	public void response(WeixinView view){
		try {
			this.getReplyStream().write(view.toWeixinStr().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("回复时出现错误", e);
		}
	}
	
}
