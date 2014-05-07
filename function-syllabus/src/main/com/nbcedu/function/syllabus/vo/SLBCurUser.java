package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;

/***store the current login user infomation*/
@SuppressWarnings("serial")
public class SLBCurUser implements Serializable{
	private final String pid;
	private final String name;
	private final boolean admin;
	
	public SLBCurUser(String pid, String name, boolean admin) {
		super();
		this.pid = pid;
		this.name = name;
		this.admin = admin;
	}

	public String getPid() {
		return pid;
	}

	public String getName() {
		return name;
	}
	/**是否是管理员**/
	public boolean isAdmin() {
		return admin;
	}
}
