package com.nbcedu.function.cardmanage.biz.impl;

import java.util.List;

import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMCardTypeDao;
import com.nbcedu.function.cardmanage.model.CMCardType;
import com.nbcedu.function.cardmanage.vo.CardType;

public class CMCardTypeBizImpl extends BaseBizImpl<CMCardType> implements CMCardTypeBiz {

	private CMCardTypeDao cardTypeDao;

	public void setCardTypeDao(CMCardTypeDao cardTypeDao) {
		super.setDao(cardTypeDao);
		this.cardTypeDao = cardTypeDao;
	}

	public CMCardTypeDao getCardTypeDao() {
		return cardTypeDao;
	}

	@Override
	public List<CMCardType> findByType(int  type) {
		return this.cardTypeDao.find("from CMCardType c where type=?", type);
	}

	
}
