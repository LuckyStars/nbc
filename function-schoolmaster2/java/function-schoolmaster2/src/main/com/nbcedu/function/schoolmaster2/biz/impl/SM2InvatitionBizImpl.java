package com.nbcedu.function.schoolmaster2.biz.impl;


import com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.dao.SM2InvatitionDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;

public class SM2InvatitionBizImpl extends BaseBizImpl<TSm2Invatition> implements SM2InvatitionBiz{

	protected SM2InvatitionDao sm2InvatitionDao ; 

	public void setSm2InvatitionDao(SM2InvatitionDao sm2InvatitionDao) {
		super.setDao(sm2InvatitionDao);
		this.sm2InvatitionDao = sm2InvatitionDao;
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findByCreaterId(java.lang.String)
	 */
	@Override
	public PagerModel findByCreaterId(String createrId) {
		String hql = "from TSm2Invatition s where s.createrId=? order by s.createTime desc";
		return this.sm2InvatitionDao.searchPaginated(hql,createrId);
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findByInvatId(java.lang.String)
	 */
	@Override
	public PagerModel findByInvatId(String invatId) {
		String hql = "from TSm2Invatition s where s.invatId=? order by s.createTime desc";
		return this.sm2InvatitionDao.searchPaginated(hql,invatId);
	}
}
