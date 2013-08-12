package com.nbcedu.function.teachersignup.biz.impl;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.dao.TSActivityDao;
import com.nbcedu.function.teachersignup.model.TSActivity;


public class TSActivityBizImpl extends BaseBizImpl<TSActivity> implements TSActivityBiz{
	private TSActivityDao actDao;

	
	
	public void setActDao(TSActivityDao actDao) {
		super.setDao(actDao);
		this.actDao = actDao;
	}


	@Override
	public void addOrUpdate(TSActivity activity, String[] subjects,
			String[] rewards) {
		// TODO Auto-generated method stub
		
	}
}
