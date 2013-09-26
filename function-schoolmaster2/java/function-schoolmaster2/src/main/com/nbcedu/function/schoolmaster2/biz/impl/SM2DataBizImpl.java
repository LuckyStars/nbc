package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.schoolmaster2.biz.SM2DataBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.dao.SM2DataDao;
import com.nbcedu.function.schoolmaster2.data.model.SM2Datas;

public class SM2DataBizImpl extends BaseBizImpl<SM2Datas> implements SM2DataBiz{

	private SM2DataDao sm2DataDao;
	public void setSm2DataDao(SM2DataDao dataDao) {
		super.setDao(dataDao);
		this.sm2DataDao = dataDao;
	}

	@Override
	public PagerModel findPageByMatcher(String matcher) {
		return this.sm2DataDao.searchPaginated(
				sm2DataDao.createCriteria(
						Restrictions.eq("matcher", matcher)
					)
			);
	}
	@Override
	public PagerModel findPageByModel(SM2Datas m) {
		StringBuffer hql = new StringBuffer("from SM2Datas where matcher=? ");
		List<Object> list = new ArrayList<Object>();
		list.add(m.getMatcher());
		if(m.getStartDate()!=null&&!StringUtil.isBlank(m.getStartDate().toString())){
			hql.append(" and createDate >? ");
			list.add(m.getCreateDate());
		}
		if(m.getEndDate()!=null&&!StringUtil.isBlank(m.getEndDate().toString())){
			hql.append(" and createDate <? ");
			list.add(m.getEndDate());
		}
		hql.append(" order by createDate desc");
		Object[] params = new Object[list.size()];
		list.toArray(params);
		return this.sm2DataDao.searchPaginated(hql.toString(),params);
	}
}
