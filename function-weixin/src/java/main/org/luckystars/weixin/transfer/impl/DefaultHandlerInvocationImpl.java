package org.luckystars.weixin.transfer.impl;

import java.util.Iterator;

import org.luckystars.weixin.transfer.HandlerContext;
import org.luckystars.weixin.transfer.HandlerInvocation;
import org.luckystars.weixin.transfer.interfaces.Handler;
import org.luckystars.weixin.transfer.interfaces.Handler.HandleResult;

public class DefaultHandlerInvocationImpl implements HandlerInvocation {

	private Iterator<Handler> handlers;
	
	public DefaultHandlerInvocationImpl(Iterator<Handler> handlers){
		this.handlers = handlers;
	}
	
	
	@Override
	public HandleResult invokeNext() {
		return null;
	}

	@Override
	public HandlerContext getInvocationContext() {
		return HandlerContext.getContext();
	}

	@Override
	public void stopChain() {
	}

}
