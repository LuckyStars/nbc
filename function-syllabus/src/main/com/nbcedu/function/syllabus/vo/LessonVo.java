package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;


public class LessonVo implements Serializable{
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	private static final long serialVersionUID = 2691043358396653354L;
	private String id;
	private Integer dayOfCycle;
	private Integer indexOfDay;
	private String classId;
	private String courseId;
	private String teacherName;
	private String courseName;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDayOfCycle() {
		return dayOfCycle;
	}
	public void setDayOfCycle(Integer dayOfCycle) {
		this.dayOfCycle = dayOfCycle;
	}
	public Integer getIndexOfDay() {
		return indexOfDay;
	}
	public void setIndexOfDay(Integer indexOfDay) {
		this.indexOfDay = indexOfDay;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}
