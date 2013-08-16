package com.nbcedu.function.teachersignup.constants;

import com.nbcedu.function.teachersignup.util.StatusEnum;

/**
 * 活动的状态
 * @author xuechong
 */
public enum ActStatus implements StatusEnum{
	/**未发布*/
	EDITING(0x0,"未发布"),
	/**已发布*/
	PUBLISHED(0x1,"已发布"),
	/**暂停*/
	PAUSED(0x2,"暂停"),
	/**结束*/
	FINISHED(0x3,"结束");
	
	private final int id;
	private final String name;
	
	private ActStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static ActStatus findById(int id){
		for (ActStatus as : ActStatus.values()) {
			if(as.id == id){return as;}
		}
		return null;
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
