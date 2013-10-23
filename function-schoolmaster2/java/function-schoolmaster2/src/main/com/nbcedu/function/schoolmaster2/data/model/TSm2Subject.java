package com.nbcedu.function.schoolmaster2.data.model;

import java.sql.Timestamp;

public class TSm2Subject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Timestamp createTime;
	private String content;
	private Integer flag;
	private String departmentId;
	private String title;
	private String moduleId;
	private Timestamp lastUpdateTime;
	private String parentId;

	// Constructors

	/** default constructor */
	public TSm2Subject() {
	}

	/** full constructor */
	public TSm2Subject(String createrId, Timestamp createTime, String content,
			Integer flag, String departmentId, String title, String moduleId,
			Timestamp lastUpdateTime, String parentId) {
		this.createrId = createrId;
		this.createTime = createTime;
		this.content = content;
		this.flag = flag;
		this.departmentId = departmentId;
		this.title = title;
		this.moduleId = moduleId;
		this.lastUpdateTime = lastUpdateTime;
		this.parentId = parentId;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}