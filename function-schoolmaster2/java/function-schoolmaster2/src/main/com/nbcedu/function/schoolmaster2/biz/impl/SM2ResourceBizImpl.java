package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.dao.SM2ResourceDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Resource;

public class SM2ResourceBizImpl extends BaseBizImpl<TSm2Resource> implements SM2ResourceBiz{
	private SM2ResourceDao sm2ResourceDao;

	/**
	 * @param sm2ResourceDao the sm2ResourceDao to set
	 */
	public void setSm2ResourceDao(SM2ResourceDao sm2ResourceDao) {
		super.setDao(sm2ResourceDao);
		this.sm2ResourceDao = sm2ResourceDao;
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz#findByInvatitionId(java.lang.String)
	 */
	@Override
	public List<TSm2Resource> findByInvatitionId(String invatitionId) {
		String hql = "from TSm2Resource s where s.progressId = ?";
		return sm2ResourceDao.find(hql, invatitionId);
	}

	@Override
	public void removeByProgId(String progId) {
		this.sm2ResourceDao.createQuery("delete from TSm2Resource where progressId=?", progId).executeUpdate();
	}

	@Override
	public PagerModel findResource(String progId,int type) {
		return this.sm2ResourceDao.searchPaginated("from TSm2Resource where progressId=? and type="+type, progId);
	}
	@Override
	public List<TSm2Resource> findAllResource(String progId,int type) {
		return this.sm2ResourceDao.find("from TSm2Resource where progressId=? and type="+type, progId);
	}
	@Override
	public void addAll(List<TSm2Resource> l) {
		for(TSm2Resource r : l){
			this.sm2ResourceDao.save(r);
		}
	}
}
