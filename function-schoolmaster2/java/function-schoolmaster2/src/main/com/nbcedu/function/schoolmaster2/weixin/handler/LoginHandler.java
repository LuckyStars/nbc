package com.nbcedu.function.schoolmaster2.weixin.handler;

import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.JSPView;
import org.luckystars.weixin.framework.api.View;
import org.luckystars.weixin.transfer.incomemsg.EventMsg;

import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;
import com.nbcedu.function.schoolmaster2.weixin.constants.Constants;


/**
 * 登陆验证
 * @author xuechong
 */
public class LoginHandler implements Handler{
	
	private String loginEventKey = "";//TODO
	
	private Sm2WeixinUserBiz wuBiz ;
	
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {
		if(matchLogin(invocation)){
			return doHandle(invocation);
		}else{
			return invocation.invokeNext();
		}
	}

	
	private HandleResult doHandle(HandlerInvocation invocation) {
		View view  = null;
		
		if(checkLoginStat()){
			view = new JSPView("", false);//跳转到欢迎界面 TODO
		}else{
			view = new JSPView("", false);//跳转到登陆 TODO
		}
		
		return invocation.createResult(view);
	}

	private boolean checkLoginStat(){
		String openId = 
			HandlerContext.getContext().getSession().getSessionId();
		String uid = this.wuBiz.findLoginUidByOpenId(openId);
		
		boolean result = uid!=null && !uid.trim().isEmpty();
		if(result){
			HandlerContext.getContext().getSession()
				.putAttr(Constants.SESSION_LOGIN_UID, uid);
		}
		return result;
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
	public void setWuBiz(Sm2WeixinUserBiz wuBiz) {
		this.wuBiz = wuBiz;
	}
	
	
}
