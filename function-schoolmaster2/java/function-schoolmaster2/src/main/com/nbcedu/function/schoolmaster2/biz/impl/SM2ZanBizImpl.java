package com.nbcedu.function.schoolmaster2.biz.impl;

import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2ZanDao;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;

public class SM2ZanBizImpl extends BaseBizImpl<Sm2Zan> implements SM2ZanBiz{

	private SM2ZanDao zanDao ;

	@Override
	public Sm2Zan findByProgUid(String progId, String uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Object addOrUpdate(Sm2Zan zan) {
		Sm2Zan temp = this.findByProgUid(zan.getProgressId(), zan.getUserUid());
		if(temp==null){
			return this.add(zan);
		}else{
			return temp;
		}
	}
	
	////////////////////////
	///getters&setters/////
	/////////////////////
	public void setZanDao(SM2ZanDao zanDao) {
		super.setDao(zanDao);
		this.zanDao = zanDao;
	}

	
	
	
}
