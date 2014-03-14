package com.nbcedu.function.schoolmaster2.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.nbcedu.function.schoolmaster2.utils.UCService;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	/**
	 * 查询教师树过滤掉校长与自己
	 */
	public void tree() {
		 List<PersonVo> p = Utils.getAllSchoolMaster();
		 List<String> checkedUids = new ArrayList<String>();
		 for(PersonVo person : p){
			 checkedUids.add(person.getUid());
		 }
		 checkedUids.add(getUserId());
		 String result = UCService.getPersonJson(checkedUids,true);
		 Struts2Utils.renderText(result, "encoding:UTF-8");
	}
	
	public void findAllMaster1() {
		Collection<PersonVo> ps = Utils.getAllSchoolMaster();
		StringBuilder s = new StringBuilder("[");
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
	
	public void findAllManager(){
		Collection<PersonVo> ps = Utils.getAllManager();
		StringBuilder s = new StringBuilder("[");
		Object[] p = ps.toArray();
		for (int i = 0; i < p.length; i++) {
			PersonVo pp = (PersonVo) p[i];
			s.append("{\"id\":\"");
			s.append(pp.getUid());
			s.append("\",\"text\":\"");
			s.append(pp.getName());
			if (i == p.length - 1) {
				s.append("\"}");
			} else {
				s.append("\"},");
			}
		}
		s.append("]");
		Struts2Utils.renderJson(s.toString(), "encoding:UTF-8");
	}
	public void findAllMaster(){
		List<PersonVo> ps = Utils.getAllSchoolMaster();
		List<PersonVo> ps1 = Utils.getAllManager();
		StringBuilder sb = new StringBuilder("[");
		sb.append(appendChild("1","校长",ps)).append(",");
		sb.append(appendChild("2","主任",ps1)).append("]");
		Struts2Utils.renderJson(sb.toString(), "encoding:UTF-8");
	}
	
	public  String appendChild(final String id,final String text ,final List<PersonVo> ps){
		class CloumnTree{
			
			StringBuffer sb = new StringBuffer();
			
			public String getCloumnTreeString(){
				appendS(id,text,ps);
				return sb.toString();
			}
			private void appendS(String id1 ,String text1,List<PersonVo> ps1){
				this.sb.append("{");
				this.sb.append("\"id\":");
				this.sb.append("\"");
				this.sb.append(id1);
				this.sb.append("\"");
				this.sb.append(",\"text\":");
				this.sb.append("\"");
				this.sb.append(text1);
				this.sb.append("\"");
				if(ps1!=null && ps1.size()>0){
					this.sb.append(",\"children\":[");
					for(int i=0;i<ps1.size();i++){
						if(!ps1.get(i).getUid().equals(getUserId())){
							if(ps1.size()-1>i){
								appendS(ps1.get(i).getUid(),ps1.get(i).getName(),null);
								sb.append(",");
							}else if(ps1.size()-1==i){
								appendS(ps1.get(i).getUid(),ps1.get(i).getName(),null);
							}
						}else{
							if(ps1.size()-1==i){
								sb.deleteCharAt(sb.length()-1);
							}
						}
					}
					this.sb.append("]");
				}
				this.sb.append("}");
			}
		}
		String userJson = new CloumnTree().getCloumnTreeString();
		if(userJson!=null){
			userJson=userJson.replaceAll(",]","]");
		}
		return userJson;
	}
	/////////////////////////
	/////getters&setters/////
	/////////////////////////

}
 