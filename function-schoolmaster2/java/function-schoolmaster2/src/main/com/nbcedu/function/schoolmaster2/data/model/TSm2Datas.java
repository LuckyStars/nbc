package com.nbcedu.function.schoolmaster2.data.model;

import java.sql.Timestamp;

public class TSm2Datas implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private Timestamp startDate;
	private Timestamp endDate;
	private Timestamp createDate;
	private Timestamp lastUpdate;
	private Integer status;
	private String creatorUid;
	private String content;
	private String matcher;

	// Constructors

	/** default constructor */
	public TSm2Datas() {
	}

	/** full constructor */
	public TSm2Datas(String title, Timestamp startDate, Timestamp endDate,
			Timestamp createDate, Timestamp lastUpdate, Integer status,
			String creatorUid, String content, String matcher) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.status = status;
		this.creatorUid = creatorUid;
		this.content = content;
		this.matcher = matcher;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatorUid() {
		return this.creatorUid;
	}

	public void setCreatorUid(String creatorUid) {
		this.creatorUid = creatorUid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMatcher() {
		return this.matcher;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

}