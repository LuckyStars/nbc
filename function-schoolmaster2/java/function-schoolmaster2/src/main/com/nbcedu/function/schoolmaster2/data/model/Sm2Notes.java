package com.nbcedu.function.schoolmaster2.data.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Sm2Notes implements Serializable{
	
	private String id;
	private String content;
	private String toppx;
	private String leftpx;
	private String color;
	private String parentId;
	private String userUid;
	private String userName;
	private Date createTime;
	
	/////////////////////////
	////get/set///////////
	////////////////////
	/**for parse json**/
	public String getTop() {
		return toppx;
	}
	public void setTop(String top) {
		this.toppx = top;
	}
	public String getLeft() {
		return leftpx;
	}
	public void setLeft(String left) {
		this.leftpx = left;
	}
	/**for parse json**/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToppx() {
		return toppx;
	}
	public void setToppx(String toppx) {
		this.toppx = toppx;
	}
	public String getLeftpx() {
		return leftpx;
	}
	public void setLeftpx(String leftpx) {
		this.leftpx = leftpx;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
