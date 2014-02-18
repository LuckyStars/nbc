package org.luckystars.weixin.framework.config;

import java.util.HashMap;
import java.util.Map;

/**
 * &lt;handlerChain id="msgTypeHandlerChain"&gt;
 * &lt;/handlerChain&gt;
 * 
 * 
 * @author xuechong
 */
public class HandlerChainConfig {
	
	private String name = "";
	
	private Map<String, ChainMapping> allChains = new HashMap<String, ChainMapping>() ;

	public String getName() {
		return name;
	}

	public Map<String, ChainMapping> getAllChains() {
		return allChains;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAllChains(Map<String, ChainMapping> allChains) {
		this.allChains = allChains;
	}
	
	
	
}
