package com.nbcedu.function.teachersignup.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 报名事件
 * @author xuechong
 */
public class TSActivity implements Serializable{
	private String id;
	private String name;
	/**附件地址*/
	private String filePath;
	/**附件名称*/
	private String fileName;
	/**详细说明*/
	private String comment;
	/***报名开始时间*/
	private Date openDate;
	/***报名结束时间*/
	private Date endDate;
	/***创建时间*/
	private Date createDate;
	/***当前状态**/
	private Integer status;
	
	private Set<TSReward> rewards = new HashSet<TSReward>();
	private Set<TSSubject> subjects = new HashSet<TSSubject>();
	/////////////////////////
	////getters&setters////
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Set<TSReward> getRewards() {
		return rewards;
	}
	public void setRewards(Set<TSReward> rewards) {
		this.rewards = rewards;
	}
	public Set<TSSubject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<TSSubject> subjects) {
		this.subjects = subjects;
	}
	
}
