package com.nbcedu.function.weixin.transfer.interfaces;

import com.nbcedu.function.weixin.transfer.HandlerContext;

/**
 * 消息处理
 * 实现类应该保证单例情况下的线程安全
 * @author xuechong
 */
public interface Handler  {

	/**
	 * 测试是否赢处理
	 * @param msg
	 * @return
	 */
	boolean test(HandlerContext msg);
	
	
	HandleResult handle(HandlerContext msg);
	
	
	public static enum HandleResult{
		INVOKE_NEXT,SKIP_ALL;
	}
	
}
