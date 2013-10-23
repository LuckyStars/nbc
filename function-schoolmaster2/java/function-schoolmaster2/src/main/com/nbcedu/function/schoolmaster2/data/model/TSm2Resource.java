package com.nbcedu.function.schoolmaster2.data.model;

import java.sql.Timestamp;


public class TSm2Resource implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Timestamp createTime;
	private Timestamp lastUpdateTime;
	private String fileName;
	private String filePath;
	private String progressId;

	// Constructors

	/** default constructor */
	public TSm2Resource() {
	}

	/** full constructor */
	public TSm2Resource(String createrId, Timestamp createTime,
			Timestamp lastUpdateTime, String fileName, String filePath,
			String progressId) {
		this.createrId = createrId;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.fileName = fileName;
		this.filePath = filePath;
		this.progressId = progressId;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

}