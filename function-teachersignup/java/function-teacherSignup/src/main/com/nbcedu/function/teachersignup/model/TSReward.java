package com.nbcedu.function.teachersignup.model;

import java.io.Serializable;

/**
 * 奖项
 * @author xuechong
 */
public class TSReward implements Serializable{
	private String id;
	/***奖项名称*/
	private String name;
	/**活动ID*/
	private String activityId;
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
