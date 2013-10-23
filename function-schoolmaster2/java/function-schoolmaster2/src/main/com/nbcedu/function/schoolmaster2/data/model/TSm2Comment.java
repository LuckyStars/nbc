package com.nbcedu.function.schoolmaster2.data.model;

import java.sql.Timestamp;


public class TSm2Comment implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Timestamp createtime;
	private Timestamp lastUpdateTime;
	private String progressId;
	private String content;

	// Constructors

	/** default constructor */
	public TSm2Comment() {
	}

	/** full constructor */
	public TSm2Comment(String createrId, Timestamp createtime,
			Timestamp lastUpdateTime, String progressId, String content) {
		this.createrId = createrId;
		this.createtime = createtime;
		this.lastUpdateTime = lastUpdateTime;
		this.progressId = progressId;
		this.content = content;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}