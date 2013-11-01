package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.ZanVo;

/**
 * 赞action
 * @author xuechong
 *
 */
@SuppressWarnings("serial")
public class ZanAction extends BaseAction{
	
	private SM2ZanBiz zanBiz;
	private Integer zanSize = null;
	
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
		Boolean result = this.zanBiz.addOrUpdate(zan)!=null;
		Struts2Util.renderText(result.toString());
	}
	
	/**
	 * 查询有多少人赞过
	 * @author xuechong
	 */
	public void showZans(){
		List<ZanVo> zans = this.zanBiz.findByProg(this.id, zanSize);
		String json = Utils.gson.toJson(zans, 
				new TypeToken<List<ZanVo>>() {}.getType());
		Struts2Utils.renderJson(json);
	}
	
	///////////////////////
	////getters&setters////
	///////////////////////
	public void setZanBiz(SM2ZanBiz zanBiz) {
		this.zanBiz = zanBiz;
	}
}
