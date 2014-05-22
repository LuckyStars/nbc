package com.nbcedu.function.schoolmaster2.weixin.biz;
import com.nbcedu.function.schoolmaster2.weixin.model.Sm2WeixinUser;
import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;

public interface Sm2WeixinUserBiz extends BaseBiz<Sm2WeixinUser> {
	/**
	 * 增加、重新绑定用户
	 * @param user
	 */
	public void addUpdateWeixinUser(Sm2WeixinUser user);
	
	/**
	 * 根据openid查找用户uid
	 * @param openId
	 * @return
	 * @author xuechong
	 */
	public String findLoginUidByOpenId(String openId);
	
	public boolean findLoginByPassUserName(String username, String password);
	/**
	 * 注销登陆
	 * @param openId
	 * @author xuechong
	 */
	public void logOut(String openId);
}
