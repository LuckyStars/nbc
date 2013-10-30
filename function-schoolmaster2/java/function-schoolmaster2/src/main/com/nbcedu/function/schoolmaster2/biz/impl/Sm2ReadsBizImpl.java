package com.nbcedu.function.schoolmaster2.biz.impl;

import com.nbcedu.function.schoolmaster2.biz.Sm2ReadsBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2ReadsDao;
import com.nbcedu.function.schoolmaster2.data.model.SM2Reads;

public class Sm2ReadsBizImpl extends BaseBizImpl<SM2Reads> implements Sm2ReadsBiz{

	private Sm2ReadsDao readsDao;

	/////////////////////////////////
	/////getters&setters//////
	/////////////////////////
	public void setReadsDao(Sm2ReadsDao readsDao) {
		this.readsDao = readsDao;
	}
	
}
