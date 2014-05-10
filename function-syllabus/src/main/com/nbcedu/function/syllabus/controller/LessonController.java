package com.nbcedu.function.syllabus.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nbcedu.function.syllabus.service.SLBLessonService;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.utils.Constants;
import com.nbcedu.function.syllabus.utils.cache.CacheManager;
import com.nbcedu.function.syllabus.vo.ClassVO;
import com.nbcedu.function.syllabus.vo.SLBCurUser;
import com.nbcedu.function.syllabus.vo.TeacherVO;


/**
 * 课程表查看控制器
 * @author xuechong
 */
@Controller
@RequestMapping("/lesson")
@Scope("prototype")
public class LessonController {
	private static final Logger logger = Logger.getLogger(LessonController.class);
	
	@Autowired(required = true)
	private SLBLessonService slbLessonService;
	@Autowired(required = true)
	private UCService ucService;

	/**
	 * 按教师姓名查询
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author wanglei
	 */
	@RequestMapping(value = "/teacher/{teacherName}")
	public ModelAndView showTeacher(@PathVariable("teacherName") String name,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String teacherNameArray = ucService.findTeacherNameArray();
		
		request.setAttribute("teacherNameArray", teacherNameArray);
		
		TeacherVO person = ucService.findPersonByName(name);
		request.setAttribute("teacherName", person.getName());
		
		return new ModelAndView("/pages/searchTeacher.jsp", 
				"allLessons",
				slbLessonService.showTeacher(person.getPid()));
	}

	/**
	 * 按教师查询(查询自己的课程)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author xuechong
	 */
	@RequestMapping(value = "/teacher")
	public ModelAndView showTeacherFirstTime(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		String teacherName = ((SLBCurUser) request.getSession().getAttribute(
				Constants.CUR_USER_SESSIONID)).getName();
		return showTeacher(teacherName, request, response);
	}

	/**
	 * 按班级查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author wanglei
	 */
	@RequestMapping(value = "/class/{classId}")
	public ModelAndView showClass(@PathVariable("classId") String id,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		ClassVO clazz = CacheManager.getAllClasses().get(id);
		
		request.setAttribute("grades", CacheManager.getSortedGrades());
		
		request.setAttribute("className", clazz.getGradeId() + "年级" + clazz.getName());
		return new ModelAndView("/pages/timetable.jsp", "allLessons",
				slbLessonService.showClass(id));
	}
	
	/**
	 * 按班级ID查询(无classId情况)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author xuechong
	 */
	@RequestMapping(value = "/class")
	public ModelAndView showClassFirstTime(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String firstId = CacheManager.getSortedGrades().iterator().next().
						getClasses().iterator().next().getId();
		return showClass(firstId, request, response);
	}
	
	/**
	 * 更新班级列表缓存
	 * @return
	 * @author xuechong
	 */
	@RequestMapping("/cache/refresh")
	public String refreshCache(){
		CacheManager.refresh();
		return "redirect:/lesson/class";
	}
	
	
	
}
