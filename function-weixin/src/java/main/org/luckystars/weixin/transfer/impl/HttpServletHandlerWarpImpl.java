package org.luckystars.weixin.transfer.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.luckystars.weixin.transfer.HandlerInvocation;
import org.luckystars.weixin.transfer.HandlerWarp;

public class HttpServletHandlerWarpImpl implements HandlerWarp{

	private HandlerInvocation invocation;
	
	public HttpServletHandlerWarpImpl(HttpServletRequest req,HttpServletResponse resp){
		this.invocation = warpInvocation(req,resp);
		
	}
	
	private HandlerInvocation warpInvocation(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handle() {
		
		
		
	}
	
}
