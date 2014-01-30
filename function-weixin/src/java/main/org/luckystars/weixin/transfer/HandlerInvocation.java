package org.luckystars.weixin.transfer;

import org.luckystars.weixin.transfer.interfaces.Handler;

public interface HandlerInvocation {
	
	Handler.HandleResult invokeNext();
	
	HandlerContext getInvocationContext();
	
	void stopChain();
}
