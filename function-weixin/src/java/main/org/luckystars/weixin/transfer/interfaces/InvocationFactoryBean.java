package org.luckystars.weixin.transfer.interfaces;

import org.luckystars.weixin.transfer.HandlerContext;
import org.luckystars.weixin.transfer.HandlerInvocation;


public interface InvocationFactoryBean {
	HandlerInvocation buildInvocation(HandlerContext ctx);
}
