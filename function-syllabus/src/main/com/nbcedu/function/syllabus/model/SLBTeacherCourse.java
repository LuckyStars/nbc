package com.nbcedu.function.syllabus.model;

import java.io.Serializable;

public class SLBTeacherCourse implements Serializable{
	private static final long serialVersionUID = 1L;
	private String teacherPid;
	private String courseId;
	private String classId;
	////////////////////////
	/////GETTERS&SETTERS////
	////////////////////////

	public String getCourseId() {
		return courseId;
	}
	public String getTeacherPid() {
		return teacherPid;
	}
	public void setTeacherPid(String teacherPid) {
		this.teacherPid = teacherPid;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null|| ! (obj instanceof SLBTeacherCourse)){
			return false;
		}
		SLBTeacherCourse slb = (SLBTeacherCourse)obj;
		return this.getCourseId().equals(slb.getCourseId())
			&&this.getTeacherPid().equals(slb.getTeacherPid());
	}
	@Override
	public int hashCode() {
		return new String(this.getCourseId() + this.getTeacherPid()).hashCode();
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}
