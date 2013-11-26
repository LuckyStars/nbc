package com.nbcedu.function.schoolmaster2.data.model;

/**
 * 发送给校长
 * @author xuechong
 */
public class SM2SubjectMaster {
	private String id;
	private String subId;
	private String userUid;
	private Integer flag;//0:新建立,1:关注的,2:最近更新,3:普通的
	private String userName;
	/////////////////////////
	////getters&Setters//////
	/////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
