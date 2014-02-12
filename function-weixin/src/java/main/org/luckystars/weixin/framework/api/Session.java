package org.luckystars.weixin.framework.api;

import java.io.Serializable;

public interface Session extends Serializable {
	
	String getSessionId();
	
	Object getAttr(Object key);
	
	Object putAttr(Object key,Object value);
	
	long getTimeOut();
	
	void setMaxIdleTime(long ms);
	
	void refreshTime();
}
