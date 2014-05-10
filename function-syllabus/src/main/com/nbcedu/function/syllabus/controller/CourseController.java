package com.nbcedu.function.syllabus.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbcedu.function.syllabus.devcore.util.exl.ExlAnnotationUtil;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.service.SLBLessonService;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.utils.ServletUtils;
import com.nbcedu.function.syllabus.utils.cache.CacheManager;
import com.nbcedu.function.syllabus.vo.ClassVO;

/**
 * 课程表查看控制器
 * @author xuechong
 */
@Controller
@RequestMapping("/course")
@Scope("prototype")
public class CourseController {
	
private static final Logger logger = Logger.getLogger(CourseController.class);
	
	@Autowired(required = true)
	private SLBLessonService slbLessonService;
	@Autowired(required = true)
	private UCService ucService;
	/**
	 * 下载可用课程名称
	 * @param request
	 * @param response
	 * @author xuechong
	 */
	@RequestMapping("/download/coursename")
	public void getCourseNameExl(HttpServletRequest request,
			HttpServletResponse response){
		String exlName = "标准课程名称";
		OutputStream out = ServletUtils.getExlOutStream(request,response,exlName);
		ExlAnnotationUtil.export(exlName, this.ucService.findAllCourse(), out);
	}
	/**
	 * 跳转至导入
	 * @author xuechong
	 */
	@RequestMapping("/import/{classId}")
	public String gotoImport(@PathVariable("classId") String id,HttpServletRequest request){
		ClassVO clazz = CacheManager.getAllClasses().get(id);
		//如果clazz不存在？
		List<SLBLesson> listLesson = slbLessonService.findBy("classId", id);
		boolean isExist = false;
		//判断是否已经导入
		if(listLesson != null && listLesson.size() > 0){
			isExist = true;
		}
		request.setAttribute("grades", CacheManager.getSortedGrades());
		request.setAttribute("isExist",isExist);
		request.setAttribute("className", clazz.getGradeId() + "年级" + clazz.getName());
		request.setAttribute("classId", id);
		return "/pages/importExl.jsp";
	}
	/**
	 * 跳转至导入
	 * @author xuechong
	 */
	@RequestMapping("/import")
	public String gotoImportFirstTime(HttpServletRequest request){
		String firstId = CacheManager.getSortedGrades().iterator().next().
		getClasses().iterator().next().getId();
		return gotoImport(firstId, request);
	}
	
	/**
	 * 更新班级列表缓存(导入课程时)
	 * @return
	 * @author xuechong
	 */
	@RequestMapping("/cache/refresh")
	public String refreshCacheImport(){
		CacheManager.refresh();
		return "redirect:/course/import";
	}
	
}
