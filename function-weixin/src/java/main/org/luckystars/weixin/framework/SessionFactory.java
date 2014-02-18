package org.luckystars.weixin.framework;

import org.luckystars.weixin.framework.api.Session;
import org.luckystars.weixin.framework.api.SessionManager;
import org.luckystars.weixin.framework.api.impl.DefaultSessionManagerImpl;

/**
 * TODO
 * 这个怎么弄还没有想好- =!
 * @author xuechong
 */
class SessionFactory {
	
	static Session createSession(HandlerContext ctx){
		String sessionId = getSessionIdByCtx(ctx);
		Session result = ManagerHolder.getManager().createSession(sessionId);
		return result;
	}
	
	
	private static String getSessionIdByCtx(HandlerContext ctx) {
		return ctx.getMsg().getSessionId();
	}


	private static class ManagerHolder{
		
		private static final SessionManager manager 
			= new DefaultSessionManagerImpl();
		
		static SessionManager getManager(){
			 return manager;
		}
		
	}
}
