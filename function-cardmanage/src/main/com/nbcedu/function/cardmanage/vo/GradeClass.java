package com.nbcedu.function.cardmanage.vo;

import java.util.ArrayList;
import java.util.List;

public class GradeClass {

	private String id;
	private String name;
	private List<GradeClass> gradeClassList = new ArrayList<GradeClass>();
	
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
	public List<GradeClass> getGradeClassList() {
		return gradeClassList;
	}
	public void setGradeClassList(List<GradeClass> gradeClassList) {
		this.gradeClassList = gradeClassList;
	}
	
	
}
