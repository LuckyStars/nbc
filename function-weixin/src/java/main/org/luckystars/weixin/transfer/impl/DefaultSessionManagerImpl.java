package org.luckystars.weixin.transfer.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.luckystars.weixin.transfer.interfaces.Session;
import org.luckystars.weixin.transfer.interfaces.SessionManager;

public class DefaultSessionManagerImpl implements SessionManager{
	
	private Thread cleanThread  = null;
	
	private Map<String, Session> sessions = new ConcurrentHashMap<String, Session>();

	public DefaultSessionManagerImpl (){
		initClean();
	}
	
	
	@Override
	public Session getSession(String id) {
		Session result = sessions.get(id);
		if(result==null){
			result = createSession(id);
		}
		return sessions.get(id);
	}

	@Override
	public Session createSession(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void removeSession(String id){
		this.sessions.remove(id);//因为使用了ConcurrentHashMap不用同步锁
	}
	
	private final void initClean(){
		this.cleanThread = new Thread(new SessionKiller(this));
		this.cleanThread.start();
	}
	
	/**
	 * 用于清除过期session
	 * @author xuechong
	 */
	private static class SessionKiller implements Runnable{

		private DefaultSessionManagerImpl sessionManager ;
		
		public SessionKiller(DefaultSessionManagerImpl sm){
			this.sessionManager = sm;
		}
		
		@Override
		public void run() {
			synchronized (this.sessionManager.sessions) {
				for (Entry<String, Session> entry:this.sessionManager.sessions.entrySet()) {
					
				}
			}
		}
		
	}
}
