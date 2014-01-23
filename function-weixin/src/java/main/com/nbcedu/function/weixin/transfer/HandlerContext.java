package com.nbcedu.function.weixin.transfer;

public interface HandlerContext {

	void invokeNext();
	
	void stopChain();
	
	void doChain();
	
	MsgContext getMsg();
}
