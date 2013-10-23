package com.nbcedu.function.schoolmaster2.invitation.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SM2Invitation implements Serializable{

	private String id;
	private String title;
	private String content;
	private String creatorUid;
	private String creatorName;
	private String receiverUid;
	private Date createDate;
	private Integer status;
	///////////////////////
	////getters &setters///
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatorUid() {
		return creatorUid;
	}
	public void setCreatorUid(String creatorUid) {
		this.creatorUid = creatorUid;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getReceiverUid() {
		return receiverUid;
	}
	public void setReceiverUid(String receiverUid) {
		this.receiverUid = receiverUid;
	}
	
}
