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
	
	public static final HandlerChainConfig getConfgi(){
		return config;
	}
	
	private HandlerChainConfig(){}
	
	private String name;
	
	private String handlerFactoryClass;
	
	private Map<String, ChainMapping> allChains ;
	
}
