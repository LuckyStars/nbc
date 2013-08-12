package com.nbcedu.function.teachersignup.biz.impl;

import com.nbcedu.function.teachersignup.biz.TSRewardBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.dao.TSRewardDao;
import com.nbcedu.function.teachersignup.model.TSReward;

public class TSRewardBizImpl extends BaseBizImpl<TSReward> implements TSRewardBiz{
	
	private TSRewardDao rewardDao;

	public void setRewardDao(TSRewardDao rewardDao) {
		super.setDao(rewardDao);
		this.rewardDao = rewardDao;
	}
	
}
