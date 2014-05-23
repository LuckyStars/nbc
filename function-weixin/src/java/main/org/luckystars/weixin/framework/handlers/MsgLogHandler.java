package org.luckystars.weixin.framework.handlers;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;

public class MsgLogHandler implements Handler{

	private static final Logger logger = Logger.getLogger(MsgLogHandler.class);
	
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {

		logger.info("\nincomemsg : \n" + invocation.getIncomeMsg().toString());
		
		HandleResult result = invocation.invokeNext();
		
		logger.info("\return view : \n" + result.getView().toViewString());
		return result;
	}

}
