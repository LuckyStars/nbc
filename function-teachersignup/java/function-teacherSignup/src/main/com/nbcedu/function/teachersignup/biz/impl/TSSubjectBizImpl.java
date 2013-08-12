package com.nbcedu.function.teachersignup.biz.impl;

import com.nbcedu.function.teachersignup.biz.TSSubjectBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.dao.TSSubjectDao;
import com.nbcedu.function.teachersignup.model.TSSubject;

public class TSSubjectBizImpl extends BaseBizImpl<TSSubject> implements TSSubjectBiz {
	
	private TSSubjectDao subjectDao;

	public void setSubjectDao(TSSubjectDao subjectDao) {
		super.setDao(subjectDao);
		this.subjectDao = subjectDao;
	}
	
}
