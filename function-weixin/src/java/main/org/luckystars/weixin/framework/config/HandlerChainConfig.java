package org.luckystars.weixin.framework.config;

import java.util.Map;

/**
 * 
 * @author xuechong
 */
public class HandlerChainConfig {
	
	/***在全局上下文中的KEY**/
	public static final String HANDLER_CHAIN_CONFIG = "HANDLER_CHAIN_CONFIG";

	private static final HandlerChainConfig config = new HandlerChainConfig();
	
	private HandlerChainConfig(){}
	
	private String name;
	
	private String handlerFactoryClass;
	
	private Map<String, ChainMapping> allChains ;

	public String getName() {
		return name;
	}

	public String getHandlerFactoryClass() {
		return handlerFactoryClass;
	}
	public Map<String, ChainMapping> getAllChains() {
		return allChains;
	}
	
	
	
}
