package com.nbcedu.function.schoolmaster2.weixin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户映射
 * @author xuechong
 */
@SuppressWarnings("serial")
public class Sm2WeixinUser implements Serializable{

	public static final String STATUS_LOGIN= "login";
	public static final String STATUS_DEPRECATED = "deprecated";
	
	private String id;
	private String weixinId;
	private String uid;
	private String status;
	private Date lastLoginTime;
	private Date createTime;
	
	/////////////////////////
	/////GETTERS&SETTERS/////
	////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
