package com.nbcedu.function.syllabus.service;

import java.util.List;
import java.util.Map;

import com.nbcedu.function.syllabus.model.SLBTeacherCourse;
import com.nbcedu.function.syllabus.vo.TeacherVO;
import com.nbcedu.function.syllabus.vo.CourseVo;

/***用户中心查询接口*/
public interface UCService {
	/**
	 * 查询老师姓名
	 * @param uid
	 * @return
	 */
	public String findUserNameByUid(String uid);
	
	String findPidByUid(String uid);
	/**
	 * 根据name查询对象 （完全匹配）
	 * @param name
	 * @return
	 */
	public 	TeacherVO findPersonByName(String name);
	/**
	 * 查询所有老师name[]
	 * @return
	 */
	public String findTeacherNameArray();
	/**
	 * 查询所有老师姓名<br>
	 * K:pid , V:name
	 * @return
	 * @author xuechong
	 */
	public Map<String, String> findTeacherNameMap();

	public String findCourseNameById(String courseId);
	
	/**
	 * find all by classId
	 * @param classId
	 * @return
	 * @author xuechong
	 */
	public List<SLBTeacherCourse> findByClassId(String classId);
	/**
	 * find all by teacherPid
	 * @param teacherPid
	 * @return
	 * @author xuechong
	 */
	public List<SLBTeacherCourse> findByTeacherPid(String teacherPid);
	
	public List<CourseVo> findAllCourse();
}
