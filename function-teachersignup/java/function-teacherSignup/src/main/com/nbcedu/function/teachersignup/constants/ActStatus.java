package com.nbcedu.function.teachersignup.constants;

import com.nbcedu.function.teachersignup.util.StatusEnum;

/**
 * 活动的状态
 * @author xuechong
 */
public enum ActStatus implements StatusEnum{
	
	EDITING(0x0,"未发布"),
	PUBLISHED(0x1,"已发布"),
	PAUSED(0x2,"暂停"),
	FINISHED(0x3,"结束");
	
	private final int id;
	private final String name;
	
	private ActStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	////////////////////
	//////getters///////
	//////////////////
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
