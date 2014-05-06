package com.nbcedu.function.syllabus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_slb_lesson")
@SuppressWarnings("serial")
public class SLBLesson implements Serializable{
	
	@Id
	@Column(unique=true,length=32,nullable=false)
	@GeneratedValue(generator = "nbc-uuid")
    @GenericGenerator(name = "nbc-uuid",strategy="uuid")
	private String id;
	/**周期中的第几天**/
	@Column(unique=false,length=11,nullable=false,name="day_of_cycle")
	private Integer dayOfCycle;
	/**第几课时**/
	@Column(unique=false,length=11,nullable=false,name="index_of_day")
	private Integer indexOfDay;
	/**对应班级ID**/
	@Column(unique=false,length=32,nullable=false,name="class_id")
	private String classId;
	@Column(unique=false,length=32,name="course_id", nullable=false)
	private String courseId;

	////////////////////////
	/////GETTERS&SETTERS////
	////////////////////////
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
