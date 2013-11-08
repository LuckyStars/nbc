package com.nbcedu.function.schoolmaster2.biz.impl;


import com.nbcedu.function.schoolmaster2.biz.SM2TransBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2TransDao;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectTrans;

public class SM2TransBizImpl extends BaseBizImpl<SM2SubjectTrans> implements SM2TransBiz {

	private SM2TransDao transDao;


	public void setTransDao(SM2TransDao transDao) {
		super.setDao(transDao);
		this.transDao = transDao;
	}


	@Override
	public void deleteBySubId(String subId) {
		this.transDao.createQuery("delete SM2SubjectTrans where subId=?", subId);
	}
	
}
