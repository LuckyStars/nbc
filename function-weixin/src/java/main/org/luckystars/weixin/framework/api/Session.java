package org.luckystars.weixin.framework.api;

import java.io.Serializable;

public interface Session extends Serializable {
	
	String getSessionId();
	
	Object getAttr(String key);
	
	Object putAttr(String key,Object value);
	
	long getCreateTime();
	
	void setMaxInactiveInterval(long ms);
	
	long getMaxInactiveInterval();
	
	/**
	 * 上次访问时间
	 * @return
	 * @author xuechong
	 */
	public long getLastAccessedTime();
	
	/**
	 * 使会话失效，同时删除属性对象
	 * @author xuechong
	 */
	void invalidate();
	
	/**
	 * 刷新访问时间
	 * @author xuechong
	 */
	void refreshAccessTime();
}
