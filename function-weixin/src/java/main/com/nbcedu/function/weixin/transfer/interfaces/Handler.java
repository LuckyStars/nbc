package com.nbcedu.function.weixin.transfer.interfaces;

import com.nbcedu.function.weixin.transfer.HandlerContext;

public interface Handler {
	
	void handleMsg(HandlerContext ctx);
	
	
}
