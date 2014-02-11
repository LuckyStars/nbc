package org.luckystars.weixin.transfer.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.luckystars.weixin.transfer.interfaces.Session;
import org.luckystars.weixin.transfer.interfaces.SessionManager;

public class DefaultSessionManagerImpl implements SessionManager{
	
	private Map<String, Session> sessions = new ConcurrentHashMap<String, Session>();

	@Override
	public Session getSession(String id) {
		return sessions.get(id);
	}
	
	
	
}
