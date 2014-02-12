package org.luckystars.weixin.framework.handlers;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;


public class RefreshSessionHandler implements Handler{

	@Override
	public HandleResult handle(HandlerInvocation invocation) {
		invocation.getInvocationContext().getSession().refreshAccessTime();
		return invocation.invokeNext();
	}

}
