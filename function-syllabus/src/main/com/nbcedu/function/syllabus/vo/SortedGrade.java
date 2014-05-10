package com.nbcedu.function.syllabus.vo;

import java.util.List;

public class SortedGrade {
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public List<ClassVO> getClasses() {
		return classes;
	}
	public void setClasses(List<ClassVO> classes) {
		this.classes = classes;
	}
	private String gradeName;
	private List<ClassVO> classes;
}
