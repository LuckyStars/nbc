package org.luckystars.weixin.framework.api.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.luckystars.weixin.framework.api.Session;


public class DefaultSessionImpl implements Session {
	
	private final String id;
	
	private Map<Object,Object> content = new ConcurrentHashMap<Object,Object>();
	
	DefaultSessionImpl(String sessionId){
		this.id = sessionId;
	}
	
	@Override
	public Object getAttr(Object key) {
		return content.get(key);
	}

	@Override
	public Object putAttr(Object key, Object value) {
		return content.put(key, value);
	}

	@Override
	public String getSessionId() {
		return id;
	}
	
}
