package org.luckystars.weixin.framework.api.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.luckystars.weixin.framework.api.Session;


@SuppressWarnings("serial")
public class DefaultSessionImpl implements Session {
	
	private static final long DEFAULT_MAX_IDLE = 1000L*60L*20L;//20min
	private final String id;
	private final long createTime;
	private volatile long lastAccessTime ;
	private volatile long maxInactiveInterval;
	
	private Map<String,Object> content = new ConcurrentHashMap<String,Object>();
	
	DefaultSessionImpl(String sessionId){
		this(sessionId,DEFAULT_MAX_IDLE);
	}
	
	DefaultSessionImpl(String sessionId,long maxIdle){
		super();
		this.id = sessionId;
		long curTime = System.currentTimeMillis();
		this.createTime = curTime;
		this.lastAccessTime =curTime;
		this.maxInactiveInterval = maxIdle;
	}
	
	@Override
	public Object getAttr(String key) {
		return content.get(key);
	}

	@Override
	public Object putAttr(String key, Object value) {
		return content.put(key, value);
	}

	@Override
	public String getSessionId() {
		return id;
	}

	@Override
	public void setMaxInactiveInterval(long ms) {
		this.maxInactiveInterval = ms;
	}

	@Override
	public long getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	@Override
	public long getLastAccessedTime() {
		return lastAccessTime;
	}

	@Override
	public void invalidate() {
		synchronized (this) {
			this.content.clear();
		}
	}

	@Override
	public void refreshAccessTime() {
		this.lastAccessTime = System.currentTimeMillis();
	}

	@Override
	public long getCreateTime() {
		return createTime;
	}

	
}
