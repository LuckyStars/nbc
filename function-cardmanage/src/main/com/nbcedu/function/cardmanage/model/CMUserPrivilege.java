package com.nbcedu.function.cardmanage.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CMUserPrivilege implements Serializable{
	private String id;
	private String userName;
	private String userUid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	
}
