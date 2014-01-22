package com.nbcedu.function.weixin.transfer.interfaces;

import com.nbcedu.function.weixin.transfer.MsgContext;

public interface HandlerChain {
	
	void handleMsg(MsgContext msg);
	
	
}
