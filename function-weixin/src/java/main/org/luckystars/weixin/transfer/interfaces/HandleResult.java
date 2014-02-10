package org.luckystars.weixin.transfer.interfaces;

/**
 * 处理的结果
 * @author xuechong
 */
public interface HandleResult {
	/**
	 * stop the rest handlers
	 * 停止余下的handler继续处理
	 */
	public static final HandleResult STOP_CHAIN = new HandleResult() {};
	
	/**
	 * 继续执行余下的handler
	 * invoke next handler in the chain
	 */
	public static final HandleResult INVOKE_NEXT = new HandleResult() {};
	
	
	
}
