package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

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

	@Override
	public List<StepVo> findAllSteps(String subId) {
		String hql = "SELECT s.id,s.name FROM TSm2Step s WHERE s.subjectId=? ORDER BY s.lastUpdateTime DESC";
		List<Object[]> resulSet = this.sm2SubjectDao.findByHQL(hql,new Object[]{subId});
		
		return Lists.transform(resulSet, new Function<Object[], StepVo>() {
			@Override
			public StepVo apply(Object[] input) {
				StepVo vo = new StepVo();
				vo.setId(input[0].toString());
				vo.setName(input[1].toString());
				return vo;
			}
		});
		
	}
	
}
