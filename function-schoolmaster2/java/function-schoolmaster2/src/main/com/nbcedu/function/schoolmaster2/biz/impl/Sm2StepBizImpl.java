package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2StepDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

public class Sm2StepBizImpl extends BaseBizImpl<TSm2Step> implements Sm2StepBiz{

	private Sm2StepDao stepDao;

	public void setStepDao(Sm2StepDao stepDao) {
		super.setDao(stepDao);
		this.stepDao = stepDao;
	}

	@Override
	public boolean findByName(String name) {
		List<TSm2Step> l = this.stepDao.findBy("name", name);
		return l.size()>0 ? true:false;
	}

	@Override
	public List<StepVo> findBySubId(String subjectId) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT s.id,s.name ");
		hql.append("FROM TSm2Step s ");
		hql.append("WHERE s.subjectId ");
		List<Object[]> resultSet = this.stepDao.findByHQL(hql.toString(), subjectId);
		if(CollectionUtils.isEmpty(resultSet)){
			return new LinkedList<StepVo>();
		}
		return transResult(resultSet);
	}

	@Override
	public List<StepVo> findByStepId(String stepId) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT s.id,s.name ");
		hql.append("FROM TSm2Step s ");
		hql.append("WHERE s.subjectId = (SELECT st.subjectId FROM TSm2Step st WHERE st.id=?)");
		List<Object[]> resultSet = this.stepDao.findByHQL(hql.toString(), stepId);
		if(CollectionUtils.isEmpty(resultSet)){
			return new LinkedList<StepVo>();
		}
		return transResult(resultSet);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<StepVo> findByProgId(String progId) {
		Query q  = this.stepDao.getNamedQuery("find_step_by_prog");
		q.setString("progId", progId);
		List<Object[]> resultSet = q.list();
		return transResult(resultSet);
	}
	///////////////////////
	//////PRIVATE//////////
	///////////////////////
	private List<StepVo> transResult(List<Object[]> resultSet){
		return Lists.transform(resultSet, new Function<Object[], StepVo>() {
			@Override
			public StepVo apply(Object[] input) {
				StepVo result = new StepVo();
				result.setId(input[0].toString());
				result.setName(input[1].toString());
				return result;
			}
		});
	}

	public void setProgressBiz(Sm2ProgressBiz progressBiz) {
		this.progressBiz = progressBiz;
	}

	
	
}
