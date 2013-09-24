package com.nbcedu.function.schoolmaster2.biz.impl;


import org.hibernate.criterion.Restrictions;

import com.nbcedu.function.schoolmaster2.biz.SM2DataBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.dao.SM2DataDao;
import com.nbcedu.function.schoolmaster2.data.model.SM2Datas;

public class SM2DataBizImpl extends BaseBizImpl<SM2Datas> implements SM2DataBiz{

	private SM2DataDao dataDao;
	public void setDataDao(SM2DataDao dataDao) {
		super.setDao(dataDao);
		this.dataDao = dataDao;
	}

	@Override
	public PagerModel findPageByMatcher(String matcher) {
		return this.dataDao.searchPaginated(
				dataDao.createCriteria(
						Restrictions.eq("matcher", matcher)
					)
			);
	}
	
}
