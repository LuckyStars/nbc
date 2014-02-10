package org.luckystars.weixin.transfer.impl;

import java.util.Collections;
import java.util.Iterator;

import org.luckystars.weixin.transfer.HandlerContext;
import org.luckystars.weixin.transfer.HandlerInvocation;
import org.luckystars.weixin.transfer.interfaces.HandleResult;
import org.luckystars.weixin.transfer.interfaces.Handler;
import org.luckystars.weixin.transfer.msg.IncomeMessage;

public class DefaultHandlerInvocationImpl implements HandlerInvocation {

	private Iterator<Handler> handlers;
	
	public DefaultHandlerInvocationImpl(Iterator<Handler> handlers){
		this.handlers = handlers;
	}
	
	
	@Override
	public HandleResult invokeNext() {
		HandleResult result = null;
		while(this.handlers!=null&&this.handlers.hasNext()){
			result = handlers.next().handle(this);
			if(result==HandleResult.STOP_CHAIN){break;}
		}
		return result;
	}

	@Override
	public HandlerContext getInvocationContext() {
		return HandlerContext.getContext();
	}

	@Override
	public IncomeMessage getIncomeMsg() {
		return HandlerContext.getContext().getMsg();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void stopChain() {
		this.handlers = Collections.EMPTY_LIST.iterator();
	}

}
