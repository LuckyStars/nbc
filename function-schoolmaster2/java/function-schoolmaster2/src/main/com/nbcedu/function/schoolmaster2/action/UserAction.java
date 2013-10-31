package com.nbcedu.function.schoolmaster2.action;


import java.util.Collection;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.nbcedu.function.schoolmaster2.utils.UCService;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	
	public void tree() {
		String result = UCService.getPersonJsonString();
		Struts2Utils.renderText(result, "encoding:UTF-8");
	}
	public void findAllMaster() {
		 Collection<PersonVo> ps = Utils.getAllManager();
		 StringBuffer s = new StringBuffer("[");
		 Object[] p = ps.toArray();
		 for(int i=0;i<p.length;i++){
			 PersonVo pp =(PersonVo) p[i];
			 s.append("{\"id\":\"");
			 s.append(pp.getUid());
			 s.append("\",\"text\":\"");
			 s.append(pp.getName());
			 if(i==p.length-1){
				 s.append("\"}"); 
			 }else{
				s.append("\"},");
			 }
		 }
		 s.append("]");
		 Struts2Utils.renderJson(s.toString(), "encoding:UTF-8");

	}
	/////////////////////////
	/////getters&setters/////
	/////////////////////////

}
 