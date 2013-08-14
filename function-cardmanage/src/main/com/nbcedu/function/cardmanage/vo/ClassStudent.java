package com.nbcedu.function.cardmanage.vo;

import java.util.ArrayList;
import java.util.List;

public class ClassStudent {

	private String id;
	private String name;
	private List<ClassStudent> classStudentList = new ArrayList<ClassStudent>();
	
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
	public List<ClassStudent> getClassStudentList() {
		return classStudentList;
	}
	public void setClassStudentList(List<ClassStudent> classStudentList) {
		this.classStudentList = classStudentList;
	}
	
}
