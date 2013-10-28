package com.nbcedu.function.schoolmaster2.biz.impl;

import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2StepDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;

public class Sm2StepBizImpl extends BaseBizImpl<TSm2Step> implements Sm2StepBiz{

	private Sm2StepDao stepDao;

	public void setStepDao(Sm2StepDao stepDao) {
		super.setDao(stepDao);
		this.stepDao = stepDao;
	}
	
}
