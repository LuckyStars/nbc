package com.nbcedu.function.cardmanage.biz.impl;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMCardApplyDao;
import com.nbcedu.function.cardmanage.model.CMCardApply;

public class CMCardApplyBizImpl extends BaseBizImpl<CMCardApply> implements CMCardApplyBiz {
	private CMCardApplyDao cardApplyDao;

	public void setCardApplyDao(CMCardApplyDao cardApplyDao) {
		super.setDao(cardApplyDao);
		this.cardApplyDao = cardApplyDao;
	}
	
}
