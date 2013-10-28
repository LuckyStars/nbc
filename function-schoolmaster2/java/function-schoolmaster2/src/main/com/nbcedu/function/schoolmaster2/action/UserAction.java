package com.nbcedu.function.schoolmaster2.action;


import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.utils.UCService;

@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	
	public void tree() {
		String result = UCService.getPersonJsonString();
		Struts2Utils.renderText(result, "text/html;charset=UTF-8");

	}
	
	/////////////////////////
	/////getters&setters/////
	/////////////////////////

}
 