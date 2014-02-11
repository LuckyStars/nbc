package org.luckystars.weixin.transfer.interfaces;

public interface SessionManager {

	public Session getSession(String id);

	Session createSession(String id);

	void removeSession(String id);


}
