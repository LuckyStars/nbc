package org.luckystars.weixin.framework.api;

/**
 * 管理session<br>
 * @author xuechong
 */
public interface SessionManager {

	public Session getSession(String id);

	/**
	 * 此方法的实现应该保证线程安全
	 * @param id
	 * @return
	 * @author xuechong
	 */
	Session createSession(String id);

	/**
	 * 此方法的实现应该保证线程安全
	 * @param id
	 * @return
	 * @author xuechong
	 */
	void removeSession(String id);

}
