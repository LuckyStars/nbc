package com.nbcedu.function.syllabus.service;


import org.springframework.web.multipart.MultipartFile;

import com.nbcedu.function.syllabus.devcore.service.BaseService;

@SuppressWarnings("unchecked")
public interface SLBUploadService extends BaseService{
	
	public boolean importLessonExl(MultipartFile file,String classId);
}
