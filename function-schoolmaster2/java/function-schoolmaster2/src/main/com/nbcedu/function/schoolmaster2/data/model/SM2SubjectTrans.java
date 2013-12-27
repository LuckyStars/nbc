package com.nbcedu.function.schoolmaster2.data.model;

/**
 * 转发
 * @author xuechong
 */
public class SM2SubjectTrans  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String userUid;
	private String userName;
	private String subId;
	private String content;
	private Integer status;//阅读状态0:未阅;1:已阅
	
	//////////////////////
	////getters&setters///
	//////////////////////
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
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
