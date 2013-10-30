package com.nbcedu.function.schoolmaster2.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读记录
 * @author xuechong
 */
@SuppressWarnings("serial")
public class SM2Reads implements Serializable{

	private String id;
	private String userUid;
	private String userName;
	private String progressId;
	private Date createTime;
	
	/**
	 * 是否为"被转发"
	 * 1为被转发人的阅读
	 */
	private Integer trans;
	//////////////////////////
	/////getters&setters/////
	/////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTrans() {
		return trans;
	}
	public void setTrans(Integer trans) {
		this.trans = trans;
	}
	
}
