package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;
import java.util.Map;

public class GradeVO implements Serializable{
	private static final long serialVersionUID = 2353314163005978467L;
	private final String id;
	private final String name;
	private final Integer gradeNum;
	private final Map<String, ClassVO> allClasses ;
	public GradeVO(String id, String name, Map<String, ClassVO> allClasses,Integer gradeNum) {
		super();
		this.id = id;
		this.name = name;
		this.allClasses = allClasses;
		this.gradeNum = gradeNum;
	}
	
	
	public Integer getGradeNum() {
		return gradeNum;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Map<String, ClassVO> getAllClasses() {
		return allClasses;
	}
}
