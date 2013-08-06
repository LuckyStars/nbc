package com.nbcedu.function.cardmanage.constants;

import com.nbcedu.function.cardmanage.util.StatusEnum;

/**
 * 申请状态
 * @author xuechong
 */
public enum CardStatus implements StatusEnum{
	
	WAITING(0x0,"待申请"),
	APPLIED(0x1,"已申请"),
	PROGRESSING(0x2,"办理中"),
	UNCOLLECTED(0x3,"未领取"),
	RECEIVED(0x4,"已领取");
	
	private CardStatus(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	private final int id;
	private final String name;
	public static CardStatus findById(int id){
		for (CardStatus cs : CardStatus.values()) {
			if(cs.id == id){return cs;}
		}
		return null;
	}
	///////////////////
	/////getters///////
	///////////////////
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
