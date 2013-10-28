package com.nbcedu.function.schoolmaster2.biz.impl;


import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;

public class SM2MasterSubBizImpl extends SM2SubjectBizImpl implements SM2MasterSubBiz{

	@Override
	public PagerModel findByMaster(final String modId, final String masterUid) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT sub.* ");
		hql.append("FROM TSm2Subject sub, ");
		hql.append("(SELECT subId FROM SM2SubjectMaster sm WHERE sm.userUid = ?) subIds ");
		hql.append("WHERE sub.id = subIds.subId ");
		hql.append("AND sub.moduleId = ? ");
		hql.append("ORDER BY sub.lastUpdateTime DESC ");
		return this.sm2SubjectDao.searchPaginated(hql.toString(),new Object[]{masterUid,modId});
	}
	
}
