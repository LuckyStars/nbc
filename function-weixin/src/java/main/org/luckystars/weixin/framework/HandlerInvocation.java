package org.luckystars.weixin.framework;

import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.IncomeMessage;

public interface HandlerInvocation {
	
	HandleResult invokeNext();
	
	HandlerContext getInvocationContext();
	
	void stopChain();
	
	IncomeMessage getIncomeMsg();
}
