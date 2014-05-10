package com.nbcedu.function.syllabus.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;
import com.nbcedu.function.syllabus.service.SLBAdminService;
import com.nbcedu.function.syllabus.service.UCService;
import com.nbcedu.function.syllabus.vo.SLBCurUser;

@Repository("syllabusLoader")
public class SyllabusLoader implements ServiceInfoLoader{

	@Autowired
	private UCService ucService;
	@Autowired
	private SLBAdminService adminService;
	
	@Override
	public Object load(Map<?, ?> param) {
		String uid = null;
		SLBCurUser curUser = null;
		uid = (String) param.get("uid");
		if (uid.equals(Constants.ADMIN_UID)) {
			curUser = new SLBCurUser(Constants.ADMIN_PID, "admin", Boolean.TRUE);
		}else{
			String userName = this.ucService.findUserNameByUid(uid);
			String pid = this.ucService.findPidByUid(uid);
			curUser = new SLBCurUser(pid, userName, this.adminService.isAdminByPid(pid));
		}
		return curUser;
	}
	
}
