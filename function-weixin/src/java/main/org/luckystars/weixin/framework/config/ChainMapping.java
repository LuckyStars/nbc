package org.luckystars.weixin.framework.config;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class ChainMapping {
	
	private String id;
	private ImmutableList<HandlerMapping> handlerList;
	
	public ChainMapping(String chainId, List<HandlerMapping> handlers) {
		this.id = chainId;
		this.handlerList = ImmutableList.copyOf(handlers);
	}
	//////////////////////////////
	//////GETTERS&SETTERS////////
	/////////////////////////////
	/**
	 * return a ImmutableList <br>
	 * should not modify the values in 
	 * @return return a ImmutableList
	 * @author xuechong
	 */
	public List<HandlerMapping> getHandlerChain() {
		return handlerList;
	}
	public String getId() {
		return id;
	}
}
