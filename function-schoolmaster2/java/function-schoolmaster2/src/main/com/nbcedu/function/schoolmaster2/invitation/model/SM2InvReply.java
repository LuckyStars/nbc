package com.nbcedu.function.schoolmaster2.invitation.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SM2InvReply implements Serializable{
	private String id;
	private String comId;
	private String content;
	private String userId;
	private String userName;
	///////////////////////
	////getters &setters///
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
