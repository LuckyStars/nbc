package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;

public class ClassVO implements Serializable{
	
	private static final long serialVersionUID = 4401476680711880378L;
	private final String id;
	private final String name;
	private final String gradeId;
	private final Integer classNum;
	
	public ClassVO(String id, String name, String gradeId,Integer classNum) {
		super();
		this.id = id;
		this.name = name;
		this.gradeId = gradeId;
		this.classNum = classNum;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGradeId() {
		return gradeId;
	}
	public Integer getClassNum() {
		return classNum;
	}
	
}
