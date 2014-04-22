package com.nbcedu.function.schoolmaster2.weixin.handler;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.impl.MsgTypeRouteInvocationFactory;
import org.luckystars.weixin.transfer.incomemsg.EventMsg;
import org.luckystars.weixin.transfer.incomemsg.WeixinMsg;

import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;


/**
 * 登陆验证
 * @author xuechong
 */
public class LoginHandler implements Handler{
	
	private String loginEventKey = "";
	
	private String sessionLoginUid = "com.nbcedu.function.schoolmaster2.weixin.handler.sessionLoginKey";
	
	private Sm2WeixinUserBiz wxUserBiz ;
	
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {
		if(matchLogin(invocation)){
			return doHandle(invocation);
		}else{
			return invocation.invokeNext();
		}
	}

	
	private HandleResult doHandle(HandlerInvocation invocation) {
		// TODO Auto-generated method stub
		return null;
	}


	private boolean matchLogin(HandlerInvocation invocation) {
		if(invocation.getIncomeMsg() instanceof EventMsg){
			EventMsg msg = (EventMsg) invocation.getIncomeMsg();
			if(msg.getEvent().equals(EventMsg.EVENT_TYPE_VIEW) 
					&& msg.getEventKey().equals(loginEventKey)){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}


	///////////////////////////
	//////GETTERS&SETTERS//////
	//////////////////////////
	public void setWxUserBiz(Sm2WeixinUserBiz wxUserBiz) {
		this.wxUserBiz = wxUserBiz;
	}
	
	
}
