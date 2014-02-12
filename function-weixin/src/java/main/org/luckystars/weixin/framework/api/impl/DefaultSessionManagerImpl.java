package org.luckystars.weixin.framework.api.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.luckystars.weixin.framework.api.Session;
import org.luckystars.weixin.framework.api.SessionManager;

public class DefaultSessionManagerImpl implements SessionManager{
	
	private Thread cleanThread  = null;
	
	private Map<String, Session> sessions = new ConcurrentHashMap<String, Session>();

	public DefaultSessionManagerImpl (){
		initCleanThread();
	}
	
	@Override
	public Session getSession(String id) {
		return sessions.get(id);
	}

	@Override
	public Session createSession(String id) {
		if(id==null){
			throw new IllegalArgumentException("不可创建id为null的Session");
		}
		Session result = null;
		synchronized (this.sessions) {
			result = this.sessions.get(id);
			if(result==null){
				result = new DefaultSessionImpl(id);
				this.sessions.put(id, result);
			}
		}
		return result;
	}
	
	@Override
	public void removeSession(String id){
		this.sessions.get(id).invalidate();
		this.sessions.remove(id);//因为使用了ConcurrentHashMap不用同步锁
	}
	
	/**
	 * 初始化管理session失效清理的线程
	 * @author xuechong
	 */
	private final void initCleanThread(){
		this.cleanThread = new Thread(new SessionKiller(this));
		this.cleanThread.start();
	}
	
	/**
	 * 用于清除过期session
	 * @author xuechong
	 */
	private static class SessionKiller implements Runnable{

		private final Long sleepTime = 1000L*60L*1L;
		
		private DefaultSessionManagerImpl sessionManager ;
		
		public SessionKiller(DefaultSessionManagerImpl sm){
			this.sessionManager = sm;
		}
		
		@Override
		public void run() {
			for(;;){
				doCleanTimeOut();
				sleep();
			}	
		}
		
		private void doCleanTimeOut(){
			synchronized (this.sessionManager.sessions) {
				if(notEmptySessions()){
					for (Entry<String, Session> entry:
							this.sessionManager.sessions.entrySet()) {
						if(isTimeOut(entry.getValue())){
							this.sessionManager.removeSession(entry.getKey());
						}
					}
				}
			}
		}
		
		private boolean notEmptySessions() {
			return this.sessionManager.sessions!=null
					&&!this.sessionManager.sessions.isEmpty();
		} 
		
		private boolean isTimeOut(Session session){
			long currentTime = System.currentTimeMillis();
			return currentTime - session.getLastAccessedTime()>session.getMaxInactiveInterval();
		}
		
		private void sleep(){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
