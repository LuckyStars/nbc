package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;


public interface InvocationFactoryBean {
	
	/**
	 * 保存在APPCONTEXT中的InvocationFactoryBean对象
	 */
	public static final String INVOCATION_FACTORY_BEAN = "INVOCATION_FACTORY_BEAN";
	
	HandlerInvocation buildInvocation(HandlerContext ctx);
	
}
