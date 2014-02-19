package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerInvocation;

/**
 * 消息处理
 * @author xuechong
 */
public interface Handler  {
	
	HandleResult handle(HandlerInvocation invocation);
	
}
