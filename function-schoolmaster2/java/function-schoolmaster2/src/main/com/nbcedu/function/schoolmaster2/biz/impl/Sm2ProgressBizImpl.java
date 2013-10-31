package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2ProgressDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;

public class Sm2ProgressBizImpl extends BaseBizImpl<TSm2Progress> implements Sm2ProgressBiz {
	
	private Sm2ProgressDao progressDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<TSm2Progress> findAllByStepId(String stepId) {
		Criteria cri = this.progressDao.createCriteria();
		cri.add(Restrictions.eq("stepId", stepId));
		return cri.list();
	}
	@Override
	public List<TSm2Progress> findByNameStepId(String stepId, String name) {
		Criteria cri = this.progressDao.createCriteria();
		cri.add(Restrictions.eq("stepId", stepId));
		cri.add(Expression.eq("name",name));
		return cri.list();
	}
	
	@Override
	public void modifyStep(String stepId, String progId) {
		TSm2Progress prog = this.progressDao.findUniqueBy("id", progId);
		if(prog!=null){
			prog.setStepId(stepId);
			this.progressDao.update(prog);
		}
	}
	
	//////////////////////////
	//////getters&setters///////
	///////////////////////
	public void setProgressDao(Sm2ProgressDao progressDao) {
		super.setDao(progressDao);
		this.progressDao = progressDao;
	}



}
