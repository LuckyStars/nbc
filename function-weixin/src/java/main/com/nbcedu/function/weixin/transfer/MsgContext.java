package com.nbcedu.function.weixin.transfer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nbcedu.function.weixin.transfer.msg.Msg;

public class MsgContext {
	
	protected Msg msg;
	
	protected HttpServletRequest req;
	
	protected HttpServletResponse resp;
	
	
	public Msg getMsg() {
		return msg;
	}
	public HttpServletRequest getReq() {
		return req;
	}
	public HttpServletResponse getResp() {
		return resp;
	}
	
}
