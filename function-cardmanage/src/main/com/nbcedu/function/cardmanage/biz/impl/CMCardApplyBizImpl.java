package com.nbcedu.function.cardmanage.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.core.util.StringUtil;
import com.nbcedu.function.cardmanage.dao.CMCardApplyDao;
import com.nbcedu.function.cardmanage.model.CMCardApply;
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
		CMCardApply app = new CMCardApply();
		BeanUtils.copyProperties(cmApply,app);
		String applyUserName=UcService.findUserNameByUid(cmApply.getApplyUserUid());
		cmApply.setApplyUserName(applyUserName);
		app.setCreateDate(new Date());
		app.setStatus(CardStatus.WAITING.getId());
		app.setCardClassId(app.getCardClassId().replaceAll("cc\\|", ""));
		super.add(app);
		return true;
	}
	@Override
	public boolean updateApply(CMApply cmApply) {
		CMCardApply c = new CMCardApply();
		BeanUtils.copyProperties(cmApply,c);
		//String applyUserName=UcService.findUserNameByUid(cmApply.getApplyUserUid());
//		c.setApplyUserName("金强");
//		c.setCreateDate(new Date());
//		c.setStatus(0);
//		c.setCardClassId(c.getCardClassId().replaceAll("cc\\|", ""));
		super.modify(c);
		return true;
	}
	@Override
	public PagerModel findAllBy(CMApply cmApply) {
		return this.cardApplyDao.findAllBy(cmApply);
	}
	@Override
	public PagerModel findAllManageBy(CMApply cmApply) {
		return pmPoToVo(this.cardApplyDao.findAllBy(cmApply));
	}
	/** 
	 * vo与po之间互相转化
	 * @param pm 待转换的分页模型
	 * @return 封装的vo分页模型
	 */ 
	@SuppressWarnings("unchecked")
	private PagerModel pmPoToVo(PagerModel pm) {
		pm.setDatas(listPoToVo(pm.getDatas()));
		return pm;
	}
	/** 
	 * vo与po之间互相转化
	 * @param list 待转化的po列表
	 * @return voList 封装的vo列表
	 */ 
	private List<CMApply> listPoToVo(List<CMCardApply> list) {
		List<CMApply> voList = new ArrayList<CMApply>();
		for (int i = 0; i < list.size(); i++) {
			CMApply mv = new CMApply();
			BeanUtils.copyProperties(list.get(i), mv);
			if (!StringUtil.isEmpty(mv.getApplyUserUid())){
				if(mv.getApplyUserUid().equals("1")){
					mv.setApplyUserName("admin");
				}else{
					mv.setApplyUserName("111");//UcService.findUserNameByUid(mv.getApplyUserUid()));
				}
			}
			if (!StringUtil.isEmpty(mv.getCardClassId())){
				mv.setCardClassName("222");//UcService.findClassByID(mv.getCardClassId()));
			}
			voList.add(mv);
		}
		return voList;
	}
}
