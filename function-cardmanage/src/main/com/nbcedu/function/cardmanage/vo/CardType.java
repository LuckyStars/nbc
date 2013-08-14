package com.nbcedu.function.cardmanage.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CardType implements Serializable{
	
	private String id;
	private String name;
	/**0为教师类型,1为学生类型*/
	private int type;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
