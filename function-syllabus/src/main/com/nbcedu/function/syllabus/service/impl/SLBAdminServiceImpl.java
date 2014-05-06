package com.nbcedu.function.syllabus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbcedu.function.syllabus.dao.SLBAdminDao;
import com.nbcedu.function.syllabus.devcore.service.impl.BaseServiceImpl;
import com.nbcedu.function.syllabus.model.SLBAdmin;
import com.nbcedu.function.syllabus.service.SLBAdminService;

@Repository("slbadminService")
@Transactional(rollbackFor=Exception.class)
public class SLBAdminServiceImpl extends BaseServiceImpl<SLBAdmin> implements SLBAdminService{
	
	private SLBAdminDao adminDao;
	
	@Autowired
	@Transactional(propagation=Propagation.NOT_SUPPORTED) 
	public void setAdminDao(SLBAdminDao adminDao) {
		this.adminDao = adminDao;
		this.setDao(adminDao);
	}

	@Override
	@Transactional(readOnly=true) 
	public boolean isAdminByPid(String pid) {
		SLBAdmin admin = this.adminDao.findUniqueBy("pid", pid);
		return admin!=null&&admin.getId()!=null;
	}
	
	
}
