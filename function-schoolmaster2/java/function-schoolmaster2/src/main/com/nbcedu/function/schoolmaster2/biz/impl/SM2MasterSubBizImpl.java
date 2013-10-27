package com.nbcedu.function.schoolmaster2.biz.impl;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;

public class SM2MasterSubBizImpl extends SM2SubjectBizImpl implements SM2MasterSubBiz{

	@Override
	public PagerModel findByMaster(String modId, String masterUid) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM ");
		
		
		return null;
	}

}
