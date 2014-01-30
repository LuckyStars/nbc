package org.luckystars.weixin.transfer.interfaces;

import org.luckystars.weixin.transfer.HandlerInvocation;

/**
 * 消息处理
 * 实现类应该保证单例情况下的线程安全
 * @author xuechong
 */
public interface Handler  {
	
	HandleResult handle(HandlerInvocation invocation);
	
	public static enum HandleResult{
		INVOKE_NEXT,SKIP_ALL;
	}
	
}
