package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;

public class TeacherVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private  String pid;
	private  String name;
	private String uid;
	public String getPid() {
		return pid;
	}
	public String getName() {
		return name;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
