package com.nbcedu.function.teachersignup.model;

import java.io.Serializable;

/**
 * 报名
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSSign implements Serializable{
	
	private String id;
	/**用户名*/
	private String userName;
	/**用户UID*/
	private String userUid;
	/**项目ID*/
	private String subjectId;
	/**获奖ID*/
	private String rewardId;
	/////////////////////////
	////getters&setters////
	///////////////////////
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getRewardId() {
		return rewardId;
	}
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}
	
}
