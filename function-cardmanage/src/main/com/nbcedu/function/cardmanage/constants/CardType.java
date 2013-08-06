package com.nbcedu.function.cardmanage.constants;

import com.nbcedu.function.cardmanage.util.StatusEnum;

/**
 * 补卡类型
 * @author xuechong
 */
public enum CardType implements StatusEnum{
	
	TEACHER(0x0,"教师卡"),
	STUDENT(0x1,"学生卡");
	
	private CardType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	private final int id;
	private final String name;
	
	public static CardType findById(int id){
		for (CardType ct : CardType.values()) {
			if(ct.id == id){return ct;}
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
