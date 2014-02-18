package org.luckystars.weixin.framework.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.HandlerFactory;
import org.luckystars.weixin.framework.api.InvocationFactoryBean;
import org.luckystars.weixin.framework.config.ChainMapping;
import org.luckystars.weixin.framework.config.HandlerChainConfig;
import org.luckystars.weixin.framework.config.HandlerMapping;
import org.luckystars.weixin.framework.util.BeanUtil;

public class MsgTypeRouteInvocationFactory implements InvocationFactoryBean {

	public static final String MSG_TYPE_HANDLERCHAIN = "msgTypeHandlerChain";
	
	public HandlerInvocation buildInvocation(HandlerContext ctx) {
		HandlerInvocation invocation = null;
		
		HandlerChainConfig config = 
			(HandlerChainConfig) ctx.getAppContext().get(MSG_TYPE_HANDLERCHAIN);
		
		String msgType = ctx.getMsg().getMsgType(); 
		Map<String, ChainMapping> mappings = config.getAllChains();
		ChainMapping chain = mappings.get(msgType);
		Iterator<Handler> iterator = buildChain(chain);
		
		invocation = new DefaultHandlerInvocationImpl(iterator);
		return invocation;
	}

	@SuppressWarnings("unchecked")
	private Iterator<Handler> buildChain(ChainMapping chain) {
		
		if(chain.getHandlerChain()!=null&&!chain.getHandlerChain().isEmpty()){
			List<Handler> handlerList = 
				new ArrayList<Handler>(chain.getHandlerChain().size());
			
			HandlerFactory factory = getHandlerFactory(chain.getHandlerFactoryClass());
			
			for (HandlerMapping mapping : chain.getHandlerChain()) {
				Handler handler = factory.build(mapping);
				handlerList.add(handler);
			}
			
			return handlerList.iterator();
		}else{
			return Collections.EMPTY_LIST.iterator();
		}
	}

	private HandlerFactory getHandlerFactory(String handlerFactoryClass) {
		
		HandlerFactory fac = null;
		try {
			fac = (HandlerFactory) BeanUtil.getBeanByClassName(handlerFactoryClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return fac;
	}

}
