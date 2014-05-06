package com.nbcedu.function.syllabus.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbcedu.function.syllabus.dao.SLBLessonDao;
import com.nbcedu.function.syllabus.devcore.service.impl.BaseServiceImpl;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.model.SLBTeacherCourse;
import com.nbcedu.function.syllabus.service.SLBLessonService;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.utils.Utils;
import com.nbcedu.function.syllabus.utils.cache.CacheManager;
import com.nbcedu.function.syllabus.vo.LessonVo;

@Repository("slbLessonService")
public class SLBLessonServiceImpl extends BaseServiceImpl<SLBLesson> implements SLBLessonService{
	
	private static final Logger logger = Logger.getLogger(SLBLessonServiceImpl.class);
	private SLBLessonDao lessonDao;
	@Autowired(required=true)
	private UCService ucService;
	
	
	@Autowired
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setLessonDao(SLBLessonDao lessonDao) {
		this.lessonDao = lessonDao;
		super.setDao(lessonDao);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List findAllBy(LessonVo t) {
		return lessonDao.findBy(t);
	}
	/** 
	 * vo与po之间互相转化
	 * @param list 待转化的po列表
	 * @return voList 封装的vo列表
	 */ 
	@SuppressWarnings({ "unused", "unchecked" })
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private List<LessonVo> listPoToVo(List<SLBLesson> list) {
		List<LessonVo> voList = list!=null&&list.size()>0?
				new ArrayList<LessonVo>(list.size()):Collections.EMPTY_LIST;
		for (SLBLesson slbLesson : list) {
			LessonVo lessonVo = new LessonVo();
			BeanUtils.copyProperties(slbLesson, lessonVo);
			voList.add(lessonVo);
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public String[][] showTeacher(String teacherPid) {
		List<SLBTeacherCourse> tList = ucService.findByTeacherPid(teacherPid);
	 	LessonVo lessonVO;
	 	String [][] lessonArray = null;
	 	
	 	boolean isEmpty= Boolean.TRUE;
	 	
	 	for(SLBTeacherCourse teacherCourse:tList){
	 		lessonVO = new LessonVo();
	 		lessonVO.setClassId(teacherCourse.getClassId());
	 		lessonVO.setCourseId(teacherCourse.getCourseId());
	 		List<SLBLesson> lessonList = findAllBy(lessonVO);
	 		
	 		int x=0,y=0;
	 		
	     	for(int i=0,end=lessonList.size();i<end;i++){
	     		SLBLesson lesson = lessonList.get(i);
	     		x = Math.max(y,lesson.getDayOfCycle());
	     		y = Math.max(x,lesson.getIndexOfDay());
	     	}
	     	
	     	if(lessonArray==null || lessonArray.length<=0){
	     		lessonArray = new String[x+1][y+1];
	     		lessonArray[0][0] = "节|周";
	     	}else{
	     		isEmpty= Boolean.FALSE;
	     		if(x>lessonArray.length||y>lessonArray[0].length){
	     			String [][] newArray = new String[x+1][y+1];
	     			for(int i=0,end=lessonArray.length;i<end;i++){
	     				newArray[i] = Arrays.copyOf(lessonArray[i], y);
	     			}
	     			lessonArray = newArray;
	     		}
	     	}
	     	
	     	for(int i=1 ; i<=x; i++){
	     		lessonArray[i][0] =  Utils.numToCharacers(i);
	     	}
	     	for(int j=1; j<=y; j++){
	     		lessonArray[0][j] = j+"";
	     	}
	     	
	     	for(int i=0,end=lessonList.size();i<end;i++){
	     		SLBLesson lesson = lessonList.get(i);
	     		int day = lesson.getIndexOfDay();
	     		int cycle = lesson.getDayOfCycle();
	     		//根据班级id查询班级 ,多个班级用|隔开
	     		if(StringUtils.isBlank(lessonArray[day][cycle])){
	     			lessonArray[day][cycle] = CacheManager.getAllClasses().
	     							get(lesson.getClassId()).getName();
	     		}else{
	     			lessonArray[day][cycle] = lessonArray[day][cycle]+
 									"|"+CacheManager.getAllClasses().
									get(lesson.getClassId()).getName();
	     		}
	     	}
	 	}
	 	
	 	if(isEmpty){
	 		lessonArray = new String[1][1];
	 		lessonArray[0][0]="没有课程数据";
	 		if(logger.isInfoEnabled()){
	 			logger.info("按教师查询课程时没有课程数据 |教师PID:" + teacherPid );
	 		}
	 	}
	 	
	 	
		return lessonArray;
	}

	@Override
	@Transactional(readOnly=true)
	public LessonVo[][] showClass(String classId) {
		LessonVo [][] result = null;
		List<SLBLesson> list = (List<SLBLesson>)lessonDao.findBy("classId",classId);  
    	if(list==null||list.isEmpty()){
    		result = new LessonVo[1][1];
    		LessonVo lesson = new LessonVo();
         	lesson.setCourseName("没有课程数据");
         	result[0][0] = lesson;
         	if(logger.isInfoEnabled()){
         		logger.info("按班级查询课程信息时没有课程数据,班级ID:" + classId);
         	}
    	}else{
    		int x=0,y=0;
         	for(int i=0,end=list.size();i<end;i++){
         		x = Math.max(x,list.get(i).getIndexOfDay());
         		y = Math.max(y,list.get(i).getDayOfCycle());
         	}
         	result = new LessonVo[x+1][y+1];
         	LessonVo head = new LessonVo();
         	head.setCourseName("节|周");
         	result[0][0] = head;
         	for(int i=1 ; i<=x; i++){
         		LessonVo lessonWeek = new LessonVo();
         		lessonWeek.setCourseName(Utils.numToCharacers(i));
         		lessonWeek.setTeacherName("");
         		result[i][0] = lessonWeek ;
         	}
        	
         	for(int j=1; j<=y; j++){
         		LessonVo lessonNum = new LessonVo();
         		lessonNum.setCourseName(j+"");
         		lessonNum.setTeacherName("");
         		result[0][j] = lessonNum;
         	}
         	
         	Map<String, String> teacherNameMap = this.ucService.findTeacherNameMap();
         	List<SLBTeacherCourse> teacherList =this.ucService.findByClassId(classId);
         	String teacherName =null;
         	for(int i=0,end=list.size();i<end;i++){
         		LessonVo lesson = new LessonVo();
         		int day = list.get(i).getIndexOfDay();
         		int cycle = list.get(i).getDayOfCycle();
         		
         		lesson.setCourseName(this.ucService.findCourseNameById(list.get(i).getCourseId()));
         		teacherName = teacherNameMap.get(
         				this.findTeacherName(list.get(i).getCourseId(),teacherList));
         		lesson.setTeacherName(StringUtils.isNotBlank(teacherName)?teacherName:"没有教师信息");
         		result[day][cycle] = lesson;
         	}
    	}
		return result;
	}

	@Transactional(readOnly=true)
	private String findTeacherName(String courseId,List<SLBTeacherCourse> teacherList) {
		for (SLBTeacherCourse tea : teacherList) {
			if (tea.getCourseId().equals(courseId)) {
				return tea.getTeacherPid();
			}
		}
		if(logger.isInfoEnabled()){
			logger.info("按班级查看时" +
					"找不到对应的教师courseID:" + courseId);
		}
		return "";
	}
}
