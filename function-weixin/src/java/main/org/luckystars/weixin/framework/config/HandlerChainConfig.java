package org.luckystars.weixin.framework.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xuechong
 */
public class HandlerChainConfig {
	
	private String name = "";
	
	private String handlerFactoryClass = "";
	
	private Map<String, ChainMapping> allChains = new HashMap<String, ChainMapping>() ;

	public String getName() {
		return name;
	}

	public String getHandlerFactoryClass() {
		return handlerFactoryClass;
	}
	public Map<String, ChainMapping> getAllChains() {
		return allChains;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHandlerFactoryClass(String handlerFactoryClass) {
		this.handlerFactoryClass = handlerFactoryClass;
	}

	public void setAllChains(Map<String, ChainMapping> allChains) {
		this.allChains = allChains;
	}
	
	
	
}
