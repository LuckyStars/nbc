package com.nbcedu.function.weixin.transfer.handler;

import java.util.LinkedList;

import com.nbcedu.function.weixin.transfer.MsgContext;
import com.nbcedu.function.weixin.transfer.interfaces.HandlerChain;
import com.nbcedu.function.weixin.transfer.interfaces.MsgHandler;

public abstract class AbstractHandlerChain implements HandlerChain{

	protected LinkedList<MsgHandler> handlerList = null;
	
	@Override
	public void handleMsg(MsgContext msg) {
		MsgHandler handler = handlerList.poll();
		handler.handle(msg);
	}

}
