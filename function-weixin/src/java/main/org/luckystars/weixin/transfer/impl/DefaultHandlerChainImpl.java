package org.luckystars.weixin.transfer.impl;

import java.util.List;

import org.luckystars.weixin.transfer.interfaces.Handler;

public class DefaultHandlerChainImpl {

	private List<Handler> handlers;
	
	public DefaultHandlerChainImpl(List<Handler> handlers){
		this.handlers = handlers;
	}
	
	
}
