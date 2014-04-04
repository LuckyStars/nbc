package org.luckystars.weixin.transfer.handler;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.transfer.incomemsg.EventMsg;


/**
 * 处理Event类型信息的Handler
 * @author xuechong
 */
public abstract class AbstractEventHandler implements Handler{

	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {
		if(check(invocation)){
			return doHandle(invocation);
		}else{
			return invocation.invokeNext();
		}
	}

	/**
	 * 处理请求
	 * @param invocation
	 * @return
	 * @author xuechong
	 */
	protected abstract HandleResult doHandle(HandlerInvocation invocation) ;
	
	/**
	 * 检查是否需要处理请求
	 * @param invocation
	 * @return
	 * @author xuechong
	 */
	protected boolean check(HandlerInvocation invocation) {
		if(!(invocation.getIncomeMsg() instanceof EventMsg)){return false;}
		EventMsg msg = (EventMsg) invocation.getIncomeMsg();
		return msg.getEventKey()!=null 
			&& getEventKey()!=null 
			&& msg.getEventKey().equals(getEventKey());
	}
	
	/**
	 * 获取事件KEY
	 * @return
	 * @author xuechong
	 */
	protected abstract String getEventKey();
	
	 
}
