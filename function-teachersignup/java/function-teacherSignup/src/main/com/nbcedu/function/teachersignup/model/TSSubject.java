package com.nbcedu.function.teachersignup.model;

import java.io.Serializable;

/**
 * 报名子项目
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSSubject implements Serializable {
	private String id;
	private String name;
	private String activityId;
	
	public TSSubject() {
		super();
	}
	public TSSubject(String name, String activityId) {
		super();
		this.name = name;
		this.activityId = activityId;
	}
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
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
}
