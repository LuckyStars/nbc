package com.nbcedu.function.schoolmaster2.weixin.biz.impl;

import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;
import com.nbcedu.function.schoolmaster2.weixin.dao.Sm2WeixinUserDao;
import com.nbcedu.function.schoolmaster2.weixin.model.Sm2WeixinUser;

public class Sm2WeixinUserBizImpl extends BaseBizImpl<Sm2WeixinUser> implements Sm2WeixinUserBiz {
	private Sm2WeixinUserDao weixinUserDao;

	public void setWeixinUserDao(Sm2WeixinUserDao weixinUserDao) {
		super.setDao(weixinUserDao);
		this.weixinUserDao = weixinUserDao;
		
	}

	@Override
	public void addUpdateWeixinUser(Sm2WeixinUser user) {
		if(!StringUtil.isEmpty(user.getWeixinId())){
			Sm2WeixinUser u = this.findWeixinUser(user.getWeixinId());
			if(u!=null){
				updateWeixinUser(0,user.getWeixinId());
			}else{
				
			}
		}
		
	}
	private int updateWeixinUser(int status,String weixinId){
		String hql  = "update Sm2WeixinUser set status = ? where weixinId = ?";
		return this.weixinUserDao.createQuery(hql,status, weixinId).executeUpdate();
	}
	/**
	 * 增加新用户
	 * @param user
	 */
	public void addWeixinUser(Sm2WeixinUser user) {
		this.weixinUserDao.save(user);
	}
	@Override
	public void modifyWeixinUserById(String id) {
		String hql  = "update Sm2WeixinUser set status = 0 where id = ?";
		this.weixinUserDao.createQuery(hql, id).executeUpdate();
		
	}

	@Override
	public void modifyWeixinUserByOpenId(String weixinId) {
		String hql  = "update Sm2WeixinUser set status = 0 where weixinId = ?";
		this.weixinUserDao.createQuery(hql, weixinId).executeUpdate();
		
	}
	

	/**
	 * 查询用户根据weixinId
	 * @param weixinId
	 * @return
	 */
	public Sm2WeixinUser findWeixinUser(String weixinId){
		return this.weixinUserDao.findUniqueBy("weixinId", weixinId);
	}
	
}
