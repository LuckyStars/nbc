package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;


public interface InvocationFactoryBean {
	HandlerInvocation buildInvocation(HandlerContext ctx);
}
