package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.List;

import org.hibernate.Criteria;
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
	
	//////////////////////////
	//////getters&setters///////
	///////////////////////
	public void setProgressDao(Sm2ProgressDao progressDao) {
		super.setDao(progressDao);
		this.progressDao = progressDao;
	}
}
