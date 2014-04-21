package com.nbcedu.function.schoolmaster2.weixin.biz.impl;

import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;
import com.nbcedu.function.schoolmaster2.weixin.dao.Sm2WeixinUserDao;
import com.nbcedu.function.schoolmaster2.weixin.model.Sm2WeixinUser;

public class Sm2WeixinUserBizImpl extends BaseBizImpl<Sm2WeixinUser> implements Sm2WeixinUserBiz {
	private Sm2WeixinUserDao weixinUserDao;

	public void setWeixinUserDao(Sm2WeixinUserDao weixinUserDao) {
		super.setDao(weixinUserDao);
		this.weixinUserDao = weixinUserDao;
		
	}
	
	
}
