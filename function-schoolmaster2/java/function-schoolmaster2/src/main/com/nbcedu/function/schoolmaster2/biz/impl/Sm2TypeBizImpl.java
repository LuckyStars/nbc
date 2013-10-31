package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.Sm2TypeBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2TypeDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;

public class Sm2TypeBizImpl extends BaseBizImpl<TSm2Type> implements Sm2TypeBiz{
	private SM2TypeDao sm2TypeDao;

	public void setSm2TypeDao(SM2TypeDao sm2TypeDao) {
		super.setDao(sm2TypeDao);
		this.sm2TypeDao = sm2TypeDao;
	}

	@Override
	public List<TSm2Type> findByModUseId(String moduleId, String userId,int mold) {
		return this.sm2TypeDao.findByModUseId(moduleId, userId, mold);
	}
	
	public List<TSm2Type> findByUserId( String userId){
		return this.sm2TypeDao.findByUserId(userId);
	}
	
}
