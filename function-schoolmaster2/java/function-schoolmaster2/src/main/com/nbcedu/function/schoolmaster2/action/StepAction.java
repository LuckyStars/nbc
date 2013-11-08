package com.nbcedu.function.schoolmaster2.action;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

@SuppressWarnings("serial")
public class StepAction extends BaseAction{

	private Sm2StepBiz stepBiz;
	
	public void stepList(){
		List<StepVo> step = this.stepBiz.findByProgId(this.id);
		Struts2Utils.renderJson(
				Utils.gson.toJson(
						step,
						new TypeToken<List<StepVo>>() {}.getType())
		);
	}
	/**
	 * 删除步骤只能删除自己建的步骤
	 * @return
	 */
	public String delete(){
		this.stepBiz.delete(id);
		return RELOAD;
	}
	
	//////////////////////////
	/////getters&setters/////
	/////////////////////////
	public void setStepBiz(Sm2StepBiz stepBiz) {
		this.stepBiz = stepBiz;
	}
}
