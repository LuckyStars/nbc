package com.nbcedu.function.schoolmaster2.invitation.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SM2InvZan implements Serializable{
	
	private String id;
	private String InvId;
	private String userName;
	private String userId;
	
	///////////////////////////
	/////getters&setters;/////
	/////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvId() {
		return InvId;
	}
	public void setInvId(String invId) {
		InvId = invId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
