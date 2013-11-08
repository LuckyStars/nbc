package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterReplyBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2MasterReplyDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterReply;

public class SM2MasterReplyBizImpl extends BaseBizImpl<TSm2MasterReply> implements SM2MasterReplyBiz{
	private SM2MasterReplyDao sm2MasterReplyDao;

	/**
	 * @param sm2MasterReplyDao the sm2MasterReplyDao to set
	 */
	public void setSm2MasterReplyDao(SM2MasterReplyDao sm2MasterReplyDao) {
		super.setDao(sm2MasterReplyDao);
		this.sm2MasterReplyDao = sm2MasterReplyDao;
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2MasterReplyBiz#findByCommentId(java.lang.String)
	 */
	@Override
	public List<TSm2MasterReply> findByCommentId(String commentId) {
		String hql = "from TSm2MasterReply s where s.progressId = ? order by s.createtime desc";
		return sm2MasterReplyDao.find(hql, commentId);
	}

	@Override
	public void removeByProgId(String progId) {
		this.sm2MasterReplyDao.createQuery("delete from TSm2MasterReply where progressId=?", progId);
		
	}
}
