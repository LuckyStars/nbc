package com.nbcedu.function.weixin.transfer.handler;

import static com.nbcedu.function.weixin.transfer.interfaces.Handler.HandleResult;

import java.util.Iterator;
import java.util.Queue;

import com.nbcedu.function.weixin.transfer.HandlerContext;
import com.nbcedu.function.weixin.transfer.interfaces.Handler;

public abstract class AbstractChainHandler implements Handler{


	protected abstract Queue<Handler> getHandlerChain();
	
	@Override
	public HandleResult handle(HandlerContext msg) {
		Iterator<Handler> handers = getHandlerChain().iterator();
		
		while (handers.hasNext()) {
			Handler handler = handers.next();
			HandleResult result = handler.handle(msg);
			if(result == HandleResult.SKIP_ALL){
				break;
			}
		}
		
		return HandleResult.SKIP_ALL;
	}

}
