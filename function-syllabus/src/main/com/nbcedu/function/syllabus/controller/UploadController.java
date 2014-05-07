package com.nbcedu.function.syllabus.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nbcedu.function.syllabus.service.SLBUploadService;
import com.nbcedu.function.syllabus.utils.Utils;
import com.nbcedu.function.syllabus.utils.cache.CacheManager;
import com.nbcedu.function.syllabus.vo.ClassVO;

/**
 * 课程表查看控制器
 * @author xuechong
 */
@Controller
@RequestMapping("/upload")
@Scope("prototype")
public class UploadController {
	
private static final Logger logger = Logger.getLogger(UploadController.class);
	@Autowired(required = true)
	private SLBUploadService slbUploadService;

	@RequestMapping(value = "/import")
	public String upload(@RequestParam(value = "file", required = true) MultipartFile file, 
			@RequestParam(value = "classId", required = true) String classId,
			HttpServletRequest request, ModelMap model) {
		//判断file是否为空
		
			String fileName = file.getOriginalFilename();
			String fileSuffix = Utils.subFileSuffix(fileName);
			if(fileSuffix.equals("xls")||fileSuffix.equals("xlsx")){
				if(file.isEmpty()){
					model.addAttribute("backContent","导入为空文件，请重新选择！");
				}else{
					boolean isSuccess = slbUploadService.importLessonExl(file,classId);
					if(isSuccess){
				    	model.addAttribute("backContent","导入成功！");
				    	model.addAttribute("isExist",true);
				    }else{
				    	model.addAttribute("backContent","导入失败，请检查格式是否正确！");
				    }
				}
			}else{
				model.addAttribute("backContent","文件类型错误，请导入Excel格式课程！");
			}
		model.addAttribute("isExistBackContent",true);
	    //组装跳转页面数据
	    ClassVO clazz = CacheManager.getAllClasses().get(classId);
	    model.addAttribute("grades", CacheManager.getSortedGrades());
	    model.addAttribute("className", clazz.getGradeId() + "年级" + clazz.getName());
	    model.addAttribute("classId", classId);
	  
	    return "/pages/importExl.jsp";
	}
}
