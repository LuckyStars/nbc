package com.nbcedu.function.syllabus.service;

import com.nbcedu.function.syllabus.devcore.service.BaseService;
import com.nbcedu.function.syllabus.model.SLBAdmin;

public interface SLBAdminService extends BaseService<SLBAdmin>{
	
	public boolean isAdminByPid(String pid);
}
