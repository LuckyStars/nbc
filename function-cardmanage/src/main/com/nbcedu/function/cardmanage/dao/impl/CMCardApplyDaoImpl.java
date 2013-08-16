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
	public PagerModel findAllBy(CMApply cmApply) {
				List<Object> paramsList = new ArrayList<Object>();
				StringBuffer hqlBuffer = new StringBuffer();
				hqlBuffer.append(" FROM CMCardApply c where 1=1 ");
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
					hqlBuffer.append(" and c.cardUserName like ? ");
					paramsList.add("%"+cmApply.getApplyUserName()+"%");
				}
				hqlBuffer.append(" ORDER BY c.createDate DESC");
				
				Object[] params = new Object[paramsList.size()];
				paramsList.toArray(params);
				return searchPaginated(hqlBuffer.toString() ,params);
	}


}
