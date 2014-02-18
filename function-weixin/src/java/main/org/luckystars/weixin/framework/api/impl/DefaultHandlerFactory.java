package org.luckystars.weixin.framework.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.HandlerFactory;
import org.luckystars.weixin.framework.config.HandlerMapping;
import org.luckystars.weixin.framework.util.BeanUtil;

public class DefaultHandlerFactory implements HandlerFactory{

	private static final Map<String, Handler> handlers = new HashMap<String, Handler>();
	private static final Logger logger = Logger.getLogger(DefaultHandlerFactory.class);
	
	@Override
	public Handler build(HandlerMapping mapping) {
		Handler handler =null;
		String className = mapping.getClassName();
		String scope = mapping.getScope();
		if(scope.equals(HandlerMapping.SCOPE_PROTOTYPE)){
			handler = getByClassName(className);
		}else{
			handler = handlers.get(className);
			if(handler==null){
				handler = getByClassName(className);
				handlers.put(className, handler);
			}
		}
		return handler;
	}
	
	private Handler getByClassName(String className){
		Handler handler =null;
		try {
			handler = (Handler) BeanUtil.getBeanByClassName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return handler;
	}

}
