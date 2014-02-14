package org.luckystars.weixin.framework.config;

import java.util.List;

import org.springframework.web.servlet.HandlerMapping;

import com.google.common.collect.ImmutableList;

public class ChainMapping {
	
	private String id;
	private ImmutableList<HandlerMapping> handlerList;
	
	//////////////////////////////
	//////GETTERS&SETTERS////////
	/////////////////////////////
	public String getType() {
		return id;
	}
	/**
	 * return a ImmutableList <br>
	 * should not modify the values in 
	 * @return return a ImmutableList
	 * @author xuechong
	 */
	public List<HandlerMapping> getHandlerChain() {
		return handlerList;
	}	

}
