package com.nbcedu.function.syllabus.vo;

import java.io.Serializable;

import com.nbcedu.function.syllabus.devcore.util.exl.annotations.*;

@ExlModel
public class CourseVo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**课程名称**/
	@ExlData(sortId = 0, title = {"名称"})
	private String name;
	private String id;
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
}
