package com.nbcedu.function.schoolmaster2.biz.impl;

import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2CommentDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Comment;

public class SM2CommentBizImpl extends BaseBizImpl<TSm2Comment> {

	private SM2CommentDao comDao;

	////////////////////////////
	////getters&setters///////
	///////////////////////////
	public void setComDao(SM2CommentDao comDao) {
		super.setDao(comDao);
		this.comDao = comDao;
	}
	
}
