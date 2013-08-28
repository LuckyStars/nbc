package com.nbcedu.function.cardmanage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.nbcedu.function.cardmanage.core.dao.impl.SimpleBaseDaoImpl;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.core.util.strings.StringUtil;
import com.nbcedu.function.cardmanage.dao.CMCardApplyDao;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.vo.CMApply;

public class CMCardApplyDaoImpl extends SimpleBaseDaoImpl<CMCardApply> implements CMCardApplyDao{

	@Override
	public PagerModel findAllBy(CMApply cmApply,String userUid) {
				List<Object> paramsList = new ArrayList<Object>();
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(" FROM CMCardApply c where c.applyUserUid=? ");
				paramsList.add(userUid);
				if (!StringUtil.isEmpty(cmApply.getCardTypeId()) ) {
					hqlBuffer.append(" and c.cardTypeId = ? ");
					paramsList.add(cmApply.getCardTypeId());
				}
				if(!StringUtil.isEmpty(cmApply.getMonth())&&StringUtil.isNumeric(cmApply.getMonth())){
					hqlBuffer.append(" and c.createDate between ? and ?");
					paramsList.add(cmApply.getBeginDate());
					paramsList.add(cmApply.getEndDate());
				}
				if (!StringUtil.isEmpty(cmApply.getCardUserName()) ) {
					hqlBuffer.append(" and c.cardUserName like ? ");
					paramsList.add("%"+cmApply.getCardUserName()+"%");
				}
				if (!StringUtil.isEmpty(cmApply.getApplyUserName()) ) {
					hqlBuffer.append(" and c.applyUserName like ? ");
					paramsList.add("%"+cmApply.getApplyUserName()+"%");
				}
				if(!StringUtil.isEmpty(cmApply.getId())){
					hqlBuffer.append(" and c.id=? ");
					paramsList.add(cmApply.getId());
				}
				if(cmApply.getStatus()!=null&&cmApply.getStatus()>=0){
					hqlBuffer.append(" and c.status=? ");
					paramsList.add(cmApply.getStatus());
				}
				hqlBuffer.append(" ORDER BY c.createDate DESC");
				
				Object[] params = new Object[paramsList.size()];
				paramsList.toArray(params);
				return searchPaginated(hqlBuffer.toString() ,params);
	}
	@Override
	public PagerModel findAllManageBy(CMApply cmApply) {
				List<Object> paramsList = new ArrayList<Object>();
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(" FROM CMCardApply c where c.status!=0 ");
				if (!StringUtil.isEmpty(cmApply.getCardTypeId()) ) {
					hqlBuffer.append(" and c.cardTypeId = ? ");
					paramsList.add(cmApply.getCardTypeId());
				}
				if(!StringUtil.isEmpty(cmApply.getMonth())&&StringUtil.isNumeric(cmApply.getMonth())){
					hqlBuffer.append(" and c.createDate between ? and ?");
					paramsList.add(cmApply.getBeginDate());
					paramsList.add(cmApply.getEndDate());
				}
				if (!StringUtil.isEmpty(cmApply.getCardUserName()) ) {
					hqlBuffer.append(" and c.cardUserName like ? ");
					paramsList.add("%"+cmApply.getCardUserName()+"%");
				}
				if (!StringUtil.isEmpty(cmApply.getApplyUserName()) ) {
					hqlBuffer.append(" and applyUserName like ? ");
					paramsList.add("%"+cmApply.getApplyUserName()+"%");
				}
				if (!StringUtil.isEmpty(cmApply.getCardClassId()) ) {
					hqlBuffer.append(" and c.cardClassId =? ");
					paramsList.add(cmApply.getCardClassId());
				}
				if (cmApply.getStatus()!=null&&cmApply.getStatus()>0) {
					hqlBuffer.append(" and c.status =? ");
					paramsList.add(cmApply.getStatus());
				}
				hqlBuffer.append(" ORDER BY c.createDate DESC");
				
				Object[] params = new Object[paramsList.size()];
				paramsList.toArray(params);
				return searchPaginated(hqlBuffer.toString() ,params);
	}

}
