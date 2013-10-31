package com.nbcedu.function.schoolmaster2.action;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.Sm2ReadsBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.ReadsVo;

/**
 * 阅读action
 * @author xuechong
 */
@SuppressWarnings("serial")
public class ReadsAction extends BaseAction{

	private Sm2ReadsBiz readsBiz;
	private Integer readsSize = null;

	/**
	 * 查看阅读
	 * @author xuechong
	 */
	public void showReads(){
		List<ReadsVo> readsList = 
			this.readsBiz.findByProg(this.id, this.readsSize);
		
		Struts2Utils.renderJson(Utils.gson.toJson(
				readsList, 
				new TypeToken<List<ReadsVo>>(){}.getType()));
	}
	
	////////////////////////
	////getters&setters////
	//////////////////////
	public void setReadsBiz(Sm2ReadsBiz readsBiz) {
		this.readsBiz = readsBiz;
	}
	public Integer getReadsSize() {
		return readsSize;
	}
	public void setReadsSize(Integer readsSize) {
		this.readsSize = readsSize;
	}
}
