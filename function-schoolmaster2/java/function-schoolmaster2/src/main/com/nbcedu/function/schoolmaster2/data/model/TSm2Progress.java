package com.nbcedu.function.schoolmaster2.data.model;

import java.sql.Timestamp;


public class TSm2Progress implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String createrId;
	private Timestamp createTime;
	private Timestamp lastUpdateTime;
	private String stepId;

	// Constructors

	/** default constructor */
	public TSm2Progress() {
	}

	/** full constructor */
	public TSm2Progress(String name, String createrId, Timestamp createTime,
			Timestamp lastUpdateTime, String stepId) {
		this.name = name;
		this.createrId = createrId;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.stepId = stepId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStepId() {
		return this.stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

}