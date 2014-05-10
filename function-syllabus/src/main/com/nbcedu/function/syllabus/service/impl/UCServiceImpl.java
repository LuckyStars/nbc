package com.nbcedu.function.syllabus.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.nbcedu.function.syllabus.model.SLBTeacherCourse;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.vo.CourseVo;
import com.nbcedu.function.syllabus.vo.TeacherVO;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcClassCourse;
import com.nbcedu.integration.uc.client.vo.NbcUcCourse;
import com.nbcedu.integration.uc.client.vo.NbcUcPerson;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

@Repository("ucService")
public class UCServiceImpl implements UCService {
	@Resource(name = "ucClient")
	private BaseClient client;
	private static final Logger logger = Logger.getLogger(UCServiceImpl.class);

	@Override
	public String findUserNameByUid(String uid) {
		NbcUcPerson person = this.client.queryPerson(1, uid);
		return person.getName();
	}

	@Override
	public String findPidByUid(String uid) {
		return client.queryUidPid(1, uid);
	}

	@Override
	public TeacherVO findPersonByName(String name) {
		List<NbcUcPerson> person = client.queryPersonByName(1, name);
		TeacherVO teacherVO = new TeacherVO();
		if (person.size() > 0) {
			BeanUtils.copyProperties(person.get(0), teacherVO);
		}
		return teacherVO;
	}

	@Override
	public String findTeacherNameArray() {
		List<NbcUcTeacher> ucTeacherList = client.queryTeacherList(4, 0, null);
		StringBuilder name = new StringBuilder(ucTeacherList.size()<<3);
		name.append("[");
		for (int i = 0; i < ucTeacherList.size(); i++) {
			name.append("&quot;");
			name.append(ucTeacherList.get(i).getName().replaceAll(" ", ""));
			name.append("&quot;");
			name.append(",");
		}
		if(name.length()>1) name.deleteCharAt(name.length()-1);
		name.append("]");
		return name.toString();
	}
	
	@Override
	public Map<String, String> findTeacherNameMap() {
		List<NbcUcTeacher> ucTeacherList = client.queryTeacherList(4, 0, null);
		Map<String, String> result = new HashMap<String, String>(ucTeacherList.size());
		for (NbcUcTeacher teacher : ucTeacherList) {
			result.put(teacher.getPid(), teacher.getName());
		}
		return result;
	}

	@Override
	public String findCourseNameById(String courseId) {
		List<CourseVo> courseList = this.findAllCourse();
		for (CourseVo course : courseList) {
			if(course.getId()
					.equals(courseId)) return course.getName();
		}
		if(logger.isInfoEnabled()){
			logger.info("查询不到课程名称:课程id为:" + courseId);
		}
		return "";
	}

	@Override
	public List<SLBTeacherCourse> findByClassId(String classId) {
		
		Map<String, String> param = new HashMap<String, String>(0x1);
		param.put("strClassId", classId);
		List<NbcUcClassCourse> cctList =this.client.queryLstClassCourses("1", "1", "0", "0", param);//查询本班下所有信息
		
		return encapeTeacherCourseList(cctList);
	}

	@SuppressWarnings("unchecked")
	private List<SLBTeacherCourse> encapeTeacherCourseList(List<NbcUcClassCourse> cctList) {
		List<SLBTeacherCourse> result = Collections.EMPTY_LIST;
		if(cctList!=null&&!cctList.isEmpty()){
			result = new ArrayList<SLBTeacherCourse>(cctList.size());
			for (NbcUcClassCourse nbcUcClassCourse : cctList) {
				SLBTeacherCourse slb = new SLBTeacherCourse();
				slb.setClassId(nbcUcClassCourse.getTuccClassid());
				slb.setCourseId(nbcUcClassCourse.getTuccCourseid());
				slb.setTeacherPid(nbcUcClassCourse.getTuccTeacherid_pid());
				result.add(slb);
			}
		}else{
			if(logger.isInfoEnabled()){
				logger.info("查询教师班级课程关系时没有数据");
			}
		}
		return result;
	}

	@Override
	public List<SLBTeacherCourse> findByTeacherPid(String teacherPid) {
		Map<String, String> param = new HashMap<String, String>(0x1);
		param.put("strTeacherId", this.findUidByPid(teacherPid));
		List<NbcUcClassCourse> cctList =this.client.queryLstClassCourses("1", "0", "1", "0", param);//查询此教师所有信息
		return encapeTeacherCourseList(cctList);
	}
	
	private String findUidByPid(String pid){
		return this.client.queryUidPid(2, pid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseVo> findAllCourse() {
		
		List<NbcUcCourse> courseList = this.client.queryLstCourses();
		if(courseList!=null && courseList.size() > 0){
			//增加*/等字符yiduishi
			CourseVo courseVo1 = new CourseVo();
			courseVo1.setId("1");
			courseVo1.setName("/");
			CourseVo courseVo2 = new CourseVo();
			courseVo2.setId("2");
			courseVo2.setName("*");
			CourseVo courseVo3 = new CourseVo();
			courseVo3.setId("3");
			courseVo3.setName("\\");
			
			
			 List<CourseVo> result = new ArrayList<CourseVo>(courseList.size()+3);
			 result.add(courseVo1);result.add(courseVo2);result.add(courseVo3);
			 for (NbcUcCourse ucCourse : courseList) {
				CourseVo courseVo = new CourseVo();
				courseVo.setId(ucCourse.getPkTUcCourseId());
				courseVo.setName(ucCourse.getTucName());
				result.add(courseVo);
			}
			return result;
		}
		return Collections.EMPTY_LIST;
	}
}
