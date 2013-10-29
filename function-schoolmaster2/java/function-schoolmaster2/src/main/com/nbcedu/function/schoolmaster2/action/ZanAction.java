package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
import com.nbcedu.function.schoolmaster2.utils.Utils;

/**
 * 赞action
 * @author xuechong
 *
 */
public class ZanAction extends BaseAction{
	
	private SM2ZanBiz zanBiz;
	
	/**
	 * 新增赞
	 * @author xuechong
	 */
	public void add(){
		Sm2Zan zan = new Sm2Zan();
		zan.setProgressId(this.id);
		zan.setUserName(Utils.curUserName());
		zan.setUserUid(getUserId());
		zan.setCreateTime(new Date());
		this.zanBiz.addOrUpdate(zan);
	}
	
	///////////////////////
	////getters&setters////
	///////////////////////
	public void setZanBiz(SM2ZanBiz zanBiz) {
		this.zanBiz = zanBiz;
	}
}
