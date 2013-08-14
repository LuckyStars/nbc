package com.nbcedu.function.cardmanage.biz.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.dao.CMCardApplyDao;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.model.CMCardType;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.CMApply;

public class CMCardApplyBizImpl extends BaseBizImpl<CMCardApply> implements CMCardApplyBiz {
	private CMCardApplyDao cardApplyDao;

	public void setCardApplyDao(CMCardApplyDao cardApplyDao) {
		super.setDao(cardApplyDao);
		this.cardApplyDao = cardApplyDao;
	}

	@Override
	public boolean add(CMApply cmApply) {
		CMCardApply c = new CMCardApply();
		BeanUtils.copyProperties(cmApply,c);
		//String applyUserName=UcService.findUserNameByUid(cmApply.getApplyUserUid());
		c.setApplyUserName("金强");
		c.setCreateDate(new Date());
		c.setStatus(0);
		c.setCardClassId(c.getCardClassId().replaceAll("cc\\|", ""));
		super.add(c);
		return true;
	}

	@Override
	public PagerModel findAllBy(CMApply cmApply) {
		return this.cardApplyDao.findAllBy(cmApply);
	}
	
}
