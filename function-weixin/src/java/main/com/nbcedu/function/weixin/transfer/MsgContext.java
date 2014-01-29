package com.nbcedu.function.weixin.transfer;

import java.io.OutputStream;

import com.nbcedu.function.weixin.transfer.msg.Msg;

public class MsgContext {
	
	protected Msg msg;
	
	/**
	 * 用于响应消息的流
	 */
	protected OutputStream out;
	
	public Msg getMsg() {
		return msg;
	}

	public OutputStream getOut() {
		return out;
	}
	
	
}
