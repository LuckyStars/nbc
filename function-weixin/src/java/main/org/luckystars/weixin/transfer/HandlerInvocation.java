package org.luckystars.weixin.transfer;

import org.luckystars.weixin.transfer.interfaces.HandleResult;
import org.luckystars.weixin.transfer.msg.IncomeMessage;

public interface HandlerInvocation {
	
	HandleResult invokeNext();
	
	HandlerContext getInvocationContext();
	
	void stopChain();
	
	IncomeMessage getIncomeMsg();
}
