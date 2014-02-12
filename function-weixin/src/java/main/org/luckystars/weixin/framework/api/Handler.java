package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerInvocation;

/**
 * 消息处理
 * 实现类应该保证单例情况下的线程安全
 * @author xuechong
 */
public interface Handler  {
	
	HandleResult handle(HandlerInvocation invocation);
	
}
