package org.luckystars.weixin.framework.config;

import java.util.List;

import com.google.common.collect.ImmutableList;


/**
 * &lt;chain id="111" handlerFactoryClass="" >
 * 
 * &lt;/chain>
 * @author xuechong
 *
 */
public class ChainMapping {
	
	private String id;
	private ImmutableList<HandlerMapping> handlerList;
	private String handlerFactoryClass = "";
	
	private static final String DEFAULT_FAC_CLASS="org.luckystars.weixin.framework.api.impl.DefaultHandlerFactory";
	
	public ChainMapping(String chainId, List<HandlerMapping> handlers) {
		super();
		this.id = chainId;
		this.handlerList = ImmutableList.copyOf(handlers);
		this.handlerFactoryClass = DEFAULT_FAC_CLASS;
	}
	
	public ChainMapping(String chainId,String facClass, List<HandlerMapping> handlers) {
		this(chainId,handlers);
		this.handlerFactoryClass = facClass;
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
	public String getHandlerFactoryClass() {
		return handlerFactoryClass;
	}
}
