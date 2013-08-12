package com.nbcedu.function.teachersignup.biz.impl;

import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.dao.TSSignDao;
import com.nbcedu.function.teachersignup.model.TSSign;

public class TSSignBizImpl extends BaseBizImpl<TSSign> implements TSSignBiz{
	private TSSignDao signDao;

	public void setSignDao(TSSignDao signDao) {
		super.setDao(signDao);
		this.signDao = signDao;
	}
	
	
}
