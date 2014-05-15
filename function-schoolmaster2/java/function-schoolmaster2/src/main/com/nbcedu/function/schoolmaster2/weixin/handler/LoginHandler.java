package com.nbcedu.function.schoolmaster2.weixin.handler;


import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.JSPView;
import org.luckystars.weixin.framework.api.View;
import org.luckystars.weixin.framework.config.xml.AppUrlLoader;
import org.luckystars.weixin.transfer.incomemsg.EventMsg;
import org.luckystars.weixin.transfer.incomemsg.WeixinMsg;
import org.luckystars.weixin.transfer.view.NewsView;
import org.luckystars.weixin.transfer.view.TextView;

import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;
import com.nbcedu.function.schoolmaster2.weixin.constants.Constants;

/**
 * 登陆验证
 * @author xuechong
 */
public class LoginHandler implements Handler{
	
	private static final Logger logger = Logger.getLogger(LoginHandler.class);
	
	private Sm2WeixinUserBiz wxUserBiz ;
	
	private final String loginUrl = "/function/function-weixin/index.jsp";
	private final String loginPicUrl = "/function/function-weixin/images/login.jpg";
	
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {
		
		
		if(!checkLoginStat()){
			IncomeMessage msg = invocation.getIncomeMsg();
			
			if(msg instanceof WeixinMsg ){
				View view =null;
				
				WeixinMsg wmsg = (WeixinMsg)msg;
				if(msg instanceof EventMsg){
					EventMsg emsg = (EventMsg)msg;
					if(emsg.getEvent().equals(EventMsg.EVENT_TYPE_VIEW)){
						view = new JSPView(getUserLoginUrl(), false);//跳转到登陆 TODO
					}
				}
				
				view = createLoginView(wmsg);
				return invocation.createResult(view);
			}
			
		}
		return invocation.invokeNext();
	}
	
	private String getCtxPath(){
		return AppContext.getContext().get(AppUrlLoader.APP_URL_KEY).toString();
	}
	
	private String getLoginPidUrl(){
		return getCtxPath() + loginPicUrl;
	}
	
	private String getUserOpenId(){
		return HandlerContext.getContext().getSession().getSessionId();
	}
	
	private String getUserLoginUrl(){
		return getCtxPath() + loginUrl + "?openId="+getUserOpenId() + "&timestamp=" + System.currentTimeMillis();
	}
	
	private View createLoginView(WeixinMsg msg) {
		View view = new TextView("您尚未登录系统\n<a href=\"" 
				+ getUserLoginUrl() + "\">点此登录</a>",msg);
		
		NewsView nview = new NewsView();
		
		return view;
	}


	/**
	 * 判断是否已经登陆过
	 * @param invocation
	 * @return
	 * @author xuechong
	 */
	private boolean checkLoginStat(){
		String openId = 
			HandlerContext.getContext().getSession().getSessionId();
		String uid = this.wxUserBiz.findLoginUidByOpenId(openId);
		
		boolean result = uid!=null && !uid.trim().isEmpty();
		if(result){
			HandlerContext.getContext().getSession()
				.putAttr(Constants.SESSION_LOGIN_UID, uid);
		}
		return result;
	}

	///////////////////////////
	//////GETTERS&SETTERS//////
	//////////////////////////
	public void setWxUserBiz(Sm2WeixinUserBiz wxUserBiz) {
		logger.info("wxUserBiz injected");
		this.wxUserBiz = wxUserBiz;
	}
	
	
}
