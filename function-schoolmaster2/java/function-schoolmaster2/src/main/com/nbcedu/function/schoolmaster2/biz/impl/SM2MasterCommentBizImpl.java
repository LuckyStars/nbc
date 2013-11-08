package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterCommentBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2MasterCommentDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterComment;

public class SM2MasterCommentBizImpl extends BaseBizImpl<TSm2MasterComment> implements SM2MasterCommentBiz{
	private SM2MasterCommentDao sm2MasterCommentDao;

	/**
	 * @param sm2MasterCommentDao the sm2MasterCommentDao to set
	 */
	public void setSm2MasterCommentDao(SM2MasterCommentDao sm2MasterCommentDao) {
		super.setDao(sm2MasterCommentDao);
		this.sm2MasterCommentDao = sm2MasterCommentDao;
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz#findByInvatitionId(java.lang.String)
	 */
	@Override
	public List<TSm2MasterComment> findByInvatitionId(String invatitionId) {
		String hql = "from TSm2MasterComment s where s.progressId = ? order by s.createtime desc";
		return sm2MasterCommentDao.find(hql, invatitionId);
	}

	@Override
	public void removeByProgId(String progId) {
		this.sm2MasterCommentDao.createQuery("delete from TSm2MasterComment where progressId=?", progId);
	}
}
