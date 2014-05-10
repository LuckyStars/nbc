package com.nbcedu.function.syllabus.service;

import java.util.List;

import com.nbcedu.function.syllabus.devcore.service.BaseService;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.vo.LessonVo;

public interface SLBLessonService extends BaseService<SLBLesson>{
	
	public List<SLBLesson> findAllBy(LessonVo t);
	
	/**
	 * 获取按教师查询页面的内容
	 * @param teacherPid
	 * @return
	 * @author wanglei
	 */
	public String [][] showTeacher(String teacherPid);
	
	/**
	 * 获取按班级查询的内容
	 * @param classId
	 * @return
	 * @author wanglei
	 */
	public LessonVo [][] showClass(String classId);
}
