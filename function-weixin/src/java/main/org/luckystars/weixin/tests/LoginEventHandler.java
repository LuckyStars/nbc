package org.luckystars.weixin.tests;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.transfer.handler.AbstractEventHandler;

public class LoginEventHandler extends AbstractEventHandler {

	@Override
	protected HandleResult doHandle(HandlerInvocation invocation) {
		
		
		return null;
	}

	@Override
	protected String getEventKey() {
		return "EVENT_LOGIN";
	}

}
