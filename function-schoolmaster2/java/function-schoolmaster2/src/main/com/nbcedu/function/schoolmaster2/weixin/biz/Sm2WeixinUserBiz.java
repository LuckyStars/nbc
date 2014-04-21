package com.nbcedu.function.schoolmaster2.weixin.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.weixin.model.Sm2WeixinUser;

public interface Sm2WeixinUserBiz extends BaseBiz<Sm2WeixinUser> {
	/**
	 * 增加、重新绑定用户
	 * @param user
	 */
	public void addUpdateWeixinUser(Sm2WeixinUser user);
	/**
	 * 根据id逻辑删除用户
	 * @param id
	 */
	public void modifyWeixinUserById(String id);
	/**
	 * 根据openId逻辑删除用户
	 * @param openId
	 */
	public void modifyWeixinUserByOpenId(String openId);

	
}
