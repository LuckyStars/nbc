package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.SM2ModuleBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2ModuleDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;

public class SM2ModuleBizImpl extends BaseBizImpl<TSm2Module> implements SM2ModuleBiz{

	private SM2ModuleDao sm2ModuleDao ; 
	
	
	public void setSm2ModuleDao(SM2ModuleDao sm2ModuleDao) {
		super.setDao(sm2ModuleDao);
		this.sm2ModuleDao = sm2ModuleDao;
	}
	
}
