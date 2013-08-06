package com.nbcedu.function.cardmanage.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CMCardType implements Serializable{
	
	private String id;
	private String name;
	/**true为教师类型,false为学生类型*/
	private boolean teacher;
}
