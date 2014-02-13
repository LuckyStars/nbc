package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;


public interface InvocationFactoryBean {
	
	public static final String INVOCATION_FACTORY_BEAN = "InvocationFactoryBean";
	
	HandlerInvocation buildInvocation(HandlerContext ctx);
	
}
