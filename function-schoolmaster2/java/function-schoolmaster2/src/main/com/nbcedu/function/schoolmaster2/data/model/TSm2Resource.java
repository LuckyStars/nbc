package com.nbcedu.function.schoolmaster2.data.model;

import java.util.Date;


public class TSm2Resource implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Date createTime;
	private Date lastUpdateTime;
	private String fileName;
	private String filePath;
	private String progressId;
	private int type;//资源类型，0：文档；1：图片；2：视频

	// Constructors

	/** default constructor */
	public TSm2Resource() {
	}

	/** full constructor */
	public TSm2Resource(String createrId, Date createTime,
			Date lastUpdateTime, String fileName, String filePath,
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}