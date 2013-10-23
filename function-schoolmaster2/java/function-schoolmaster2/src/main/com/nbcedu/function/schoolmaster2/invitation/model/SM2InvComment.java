package com.nbcedu.function.schoolmaster2.invitation.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SM2InvComment implements Serializable{

	private String id;
	private String content;
	private String creatorUid;
	private String creatorName;
	private Date createDate;
	private String invId;
	///////////////////////
	////getters &setters///
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	
}
