package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.HandlerInvocation;

/**
 * 消息处理
 * @author xuechong
 */
public interface Handler  {
	
	/**
	 * 不可返回null
	 * @param invocation
	 * @return
	 * @throws Exception 
	 * @author xuechong
	 */
	HandleResult handle(HandlerInvocation invocation) throws Exception;
	
}
