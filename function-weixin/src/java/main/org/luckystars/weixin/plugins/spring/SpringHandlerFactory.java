package org.luckystars.weixin.plugins.spring;

import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.HandlerFactory;
import org.luckystars.weixin.framework.config.HandlerMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 基于spring的handlerfactory
 * @author xuechong
 */
public class SpringHandlerFactory implements HandlerFactory{

	@Override
	public Handler build(HandlerMapping mapping) {
		return this.getContext().getBean(mapping.getClassName(),Handler.class);
	}
	
	private ApplicationContext getContext(){
		return ContextLoader.getCurrentWebApplicationContext();  
	}
	
}
