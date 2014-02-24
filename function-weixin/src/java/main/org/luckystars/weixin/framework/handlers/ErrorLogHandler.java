package org.luckystars.weixin.framework.handlers;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;


public class ErrorLogHandler implements Handler{

	
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception{
		
		HandleResult result = null;
		
		result = invocation.invokeNext();
		return result;
	}

}
