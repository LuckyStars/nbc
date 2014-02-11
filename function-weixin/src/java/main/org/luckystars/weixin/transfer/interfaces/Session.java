package org.luckystars.weixin.transfer.interfaces;

public interface Session {
	
	public Object getAttr(Object key);
	
	public Object putAttr(Object key,Object value);
	
}
