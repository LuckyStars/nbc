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
//		SQLQuery q = (SQLQuery) this.sm2SubjectDao.createSqlQuery(this.sm2SubjectDao.getNamedQuery("subject_master_module").getQueryString());
//		q.addScalar("content", Hibernate.STRING);
//		q.setString("uid", masterUid);
//		q.setString("moduleId", modId);
//		q.setFirstResult(SystemContext.getOffset());
//		q.setMaxResults(SystemContext.getPagesize());
//		
//		List<TSm2Subject> resultSet = q.list();
//		List<TSm2Subject> result = Lists.transform(resultSet, new Function<TSm2Subject, TSm2Subject>() {
//			@Override
//			public TSm2Subject apply(TSm2Subject input) {
//				
//				return null;
//			}
//		});
//		
//		Query countQ = this.sm2SubjectDao.getNamedQuery("subject_master_module_count");
//		countQ.setString("uid", masterUid);
//		countQ.setString("moduleId", modId);
//		Object count = countQ.uniqueResult();
//		
//		PagerModel pm = new PagerModel();
//		pm.setDatas(result);
//		pm.setTotal(count==null?0:Integer.valueOf(count.toString()));
//		pm.setTotalPageNo(
//				pm.getTotal()%SystemContext.getPagesize()==0?
//				pm.getTotal()/SystemContext.getPagesize():
//				pm.getTotal()/SystemContext.getPagesize()+1
//			);
//		return pm;
		
		StringBuilder hql = new StringBuilder("");
		hql.append("FROM TSm2Subject sub WHERE sub.moduleId =? ");
		hql.append("AND sub.id in (SELECT subId FROM SM2SubjectMaster m WHERE m.userUid = ?) ");
		hql.append("ORDER BY sub.createTime DESC");
		return this.sm2SubjectDao.searchPaginated(hql.toString(), new Object[]{masterUid,modId});
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
