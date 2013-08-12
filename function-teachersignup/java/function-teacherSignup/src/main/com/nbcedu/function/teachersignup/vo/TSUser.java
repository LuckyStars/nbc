package com.nbcedu.function.teachersignup.vo;

/**
 * 当前登录的用户
 * @author xuechong
 */
public class TSUser {

	private final String userName;
	private final String userUid;
	private final boolean isAdmin;
	
	public TSUser(String userName, String userUid, boolean isAdmin) {
		super();
		this.userName = userName;
		this.userUid = userUid;
		this.isAdmin = isAdmin;
	}

	//////////////////////
	//////getters////////
	/////////////////////
	public String getUserName() {
		return userName;
	}
	public String getUserUid() {
		return userUid;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	
}
