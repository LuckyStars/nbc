package com.nbcedu.function.schoolmaster2.weixin.biz.impl;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;

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
				updateWeixinUser(Sm2WeixinUser.STATUS_LOGIN,user.getWeixinId());
			}else{
				user.setStatus(user.STATUS_LOGIN);
				this.add(user);
			}
		}
		
	}
	private int updateWeixinUser(String status,String weixinId){
		String hql  = "update Sm2WeixinUser set status = ? where weixinId = ?";
		return this.weixinUserDao.createQuery(hql,status, weixinId).executeUpdate();
	}


	/**
	 * 查询用户根据weixinId
	 * @param weixinId
	 * @return
	 */
	public Sm2WeixinUser findWeixinUser(String weixinId){
		return this.weixinUserDao.findUniqueBy("weixinId", weixinId);
	}

	@Override
	public String findLoginUidByOpenId(String openId) {
		String hql = "SELECT t.uid FROM Sm2WeixinUser t WHERE t.weixinId=? AND t.status=?";
		Query q = this.weixinUserDao.createQuery(hql, new Object[]{openId,Sm2WeixinUser.STATUS_LOGIN});
		Object[] result = (Object[]) q.uniqueResult();
		if(result==null){return "";}
		return StringUtils.trimToEmpty((String)result[0]);
	}

	@Override
	public boolean findLoginByPassUserName(String username, String password) {
		String sql = "select uid from t_shijia_sso_account where username = ? and password = ? ";
		Query q = this.weixinUserDao.createSqlQuery(sql, username, password);
		Object result = q.uniqueResult();
		if(result==null){return false;}
		return true;
	}
	@Override
	public void logOut(String openId) {
		this.weixinUserDao.createQuery(
				"UPDATE Sm2WeixinUser t SET t.status='" 
				+ Sm2WeixinUser.STATUS_DEPRECATED 
				+ "' WHERE t.weixinId=?", openId).executeUpdate();
	}
	
}
