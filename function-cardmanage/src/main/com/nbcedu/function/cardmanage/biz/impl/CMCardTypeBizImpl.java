package com.nbcedu.function.cardmanage.biz.impl;

import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMCardTypeDao;
import com.nbcedu.function.cardmanage.model.CMCardType;

public class CMCardTypeBizImpl extends BaseBizImpl<CMCardType> implements CMCardTypeBiz {

	private CMCardTypeDao cardTypeDao;

	public void setCardTypeDao(CMCardTypeDao cardTypeDao) {
		super.setDao(cardTypeDao);
		this.cardTypeDao = cardTypeDao;
	}

	public CMCardTypeDao getCardTypeDao() {
		return cardTypeDao;
	}
	
}
