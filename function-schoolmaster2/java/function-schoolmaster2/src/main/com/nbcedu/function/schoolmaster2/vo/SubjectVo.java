package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;

public class SubjectVo{

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private String title;
	private String moduleId;
	private String moduleName;
	private Date beginDate;
	private Date endDate;
	private Date createTime;
	private String createrName;
	private String departmentName;
	private Integer status;
	
	/*
	 * 执行者
	 */
	private String excuteUserId;

	private String checkUserId;
	// Constructors

	/** default constructor */
	public SubjectVo() {
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


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getModuleId(){
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getExcuteUserId() {
		return excuteUserId;
	}

	public void setExcuteUserId(String excuteUserId) {
		this.excuteUserId = excuteUserId;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}