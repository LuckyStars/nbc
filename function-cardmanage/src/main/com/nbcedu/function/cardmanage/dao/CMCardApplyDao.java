package com.nbcedu.function.cardmanage.dao;

import com.nbcedu.function.cardmanage.core.dao.BaseDao;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.vo.CMApply;


public interface CMCardApplyDao extends BaseDao<CMCardApply>{
	
	public PagerModel findAllBy(CMApply cmApply);
}
