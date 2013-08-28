package com.nbcedu.function.cardmanage.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.core.util.StringUtil;
import com.nbcedu.function.cardmanage.dao.CMCardApplyDao;
import com.nbcedu.function.cardmanage.dao.CMCardTypeDao;
import com.nbcedu.function.cardmanage.dao.CMUserPrivilegeDao;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.model.CMCardType;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.cardmanage.util.PortalMsgUtil;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.CMApply;
import com.nbcedu.function.functionsupport.mapping.PortalMessage;

public class CMCardApplyBizImpl extends BaseBizImpl<CMCardApply> implements CMCardApplyBiz {
	private CMCardApplyDao cardApplyDao;
	private CMCardTypeDao cardTypeDao;
	private CMUserPrivilegeDao cmUserPrivilegeDao;


	public void setCmUserPrivilegeDao(CMUserPrivilegeDao cmUserPrivilegeDao) {
		this.cmUserPrivilegeDao = cmUserPrivilegeDao;
	}

	public void setCardApplyDao(CMCardApplyDao cardApplyDao) {
		super.setDao(cardApplyDao);
		this.cardApplyDao = cardApplyDao;
	}

	public void setCardTypeDao(CMCardTypeDao cardTypeDao) {
		this.cardTypeDao = cardTypeDao;
	}

	@Override
	public boolean add(CMApply cmApply) {
		CMCardApply app = new CMCardApply();
		BeanUtils.copyProperties(cmApply,app);
		String curUserName=UcService.findUserNameByUid(cmApply.getApplyUserUid());
		if(cmApply.getCardType().getType()!=1){
			app.setCardUserName(curUserName);
			app.setCardClassId("");
		}
		app.setApplyUserName(curUserName);
		app.setCreateDate(new Date());
		CMCardApply o = (CMCardApply)super.addOrUpdate(app);
		if(app.getStatus().intValue() == CardStatus.APPLIED.getId() && o!=null && !StringUtil.isEmpty(o.getApplyUserUid())){
			o.getCardType().setType(cmApply.getCardType().getType());
			sendApplyMessage(o);
		}
		return true;
	}
	
	private boolean sendApplyMessage(CMCardApply o){
		PortalMessage message = new PortalMessage();
		message.setFunctionName("cardManage");
		message.setModuleName("cardManage");
		Collection<String> coll = new ArrayList<String>();
		List<CMUserPrivilege> users = this.cmUserPrivilegeDao.getAll();
		CMCardType cardType = this.cardTypeDao.get(o.getCardTypeId());
		o.setCardType(cardType);
		for(CMUserPrivilege user : users){
			coll.add(user.getUserUid());
		}
		message.setTitle(o.getApplyUserName()+"老师进行了"+o.getCardType().getName()+"补卡申请!");
		message.setIntroduction(o.getApplyUserName()+"老师进行了"+o.getCardType().getName()+"补卡申请!");
		message.setReceiverUids(coll);
		message.setMessageId(o.getId());
		message.setContent("申请补卡.");
		message.setUrl("cardManage/manageList_apply.action?cmApply.id="+o.getId());
		PortalMsgUtil p = new PortalMsgUtil();
		p.send(message);
		return true;
	}
	
	@Override
	public boolean modifyApply(CMApply cmApply) {
		CMCardApply c = new CMCardApply();
		BeanUtils.copyProperties(cmApply,c);
		CMCardType card = cardTypeDao.get(cmApply.getCardTypeId());
		
		String applyUserName=UcService.findUserNameByUid(cmApply.getApplyUserUid());
		if(card.getType()==0){
			c.setCardUserName(applyUserName);
			c.setCardClassId("");
		}
		c.setApplyUserName(applyUserName);
		c.setCreateDate(new Date());
		c.setStatus(0);
		super.modify(c);
		return true;
	}
	@Override
	public PagerModel findAllBy(CMApply cmApply,String userUid) {
		return this.cardApplyDao.findAllBy(cmApply,userUid);
	}
	@Override
	public PagerModel findAllManageBy(CMApply cmApply) {
		return pmPoToVo(this.cardApplyDao.findAllManageBy(cmApply));
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
					mv.setApplyUserName(UcService.findUserNameByUid(mv.getApplyUserUid()));
				}
			}
			if (!StringUtil.isEmpty(mv.getCardClassId())){
				mv.setCardClassName(UcService.findClassByID(mv.getCardClassId()));
			}
			voList.add(mv);
		}
		return voList;
	}
}
