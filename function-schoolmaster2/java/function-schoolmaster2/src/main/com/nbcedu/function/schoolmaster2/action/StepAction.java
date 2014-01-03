package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

@SuppressWarnings("serial")
public class StepAction extends BaseAction{

	private String subjectId;
	private String name;
	private TSm2Step step ;

	private Sm2StepBiz stepBiz;
	
	public void stepList(){
		List<StepVo> step = this.stepBiz.findByProgId(this.id);
		Struts2Utils.renderJson(
				Utils.gson.toJson(
						step,
						new TypeToken<List<StepVo>>() {}.getType())
		);
	}
	
	public void findById(){
		TSm2Step s = this.stepBiz.findById(id);
		Struts2Utils.renderJson(Utils.gson.toJson(s));
	}
	
	public void add(){
		TSm2Step st;
		boolean  b = false;
		if(!StringUtil.isEmpty(name)){
			for(String n : name.split(",")){
				st = new TSm2Step();
				st.setCreaterId(this.getUserId());
				st.setCreateTime(new Date());
				st.setSubjectId(subjectId);
				st.setName(n);
				b = this.stepBiz.addStep(st);
			}
		}
		Struts2Utils.renderText(b?"0":"1","encoding:UTF-8");
	}
	
	public void isExistStep(){
		boolean b = false ;
		if(!StringUtil.isEmpty(name)){
			for(String n : name.split(",")){
				b = this.stepBiz.findByName(n,subjectId);
				if(b){
				   break;
				}
			}
		}
		Struts2Utils.renderText(b?"0":"1","encoding:UTF-8");
	}
	/**
	 * 删除步骤只能删除自己建的步骤
	 * @return
	 */
	public void delete(){
		boolean b = this.stepBiz.removeById1(id);
		Struts2Utils.renderText(b?"0":"1","encoding:UTF-8");
	}
	
	public void isExist(){
		List<StepVo> step = this.stepBiz.findByProgId(this.id);
		Struts2Utils.renderText((step != null && step.size()>0)?"0":"1","encoding:UTF-8");
		
	}
	
	public void update(){
		boolean b =this.stepBiz.updateBySubId(step);
		Struts2Utils.renderText(b?"0":"1","encoding:UTF-8");
	}
	
	//////////////////////////
	/////getters&setters/////
	/////////////////////////
	public void setStepBiz(Sm2StepBiz stepBiz) {
		this.stepBiz = stepBiz;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TSm2Step getStep() {
		return step;
	}
	public void setStep(TSm2Step step) {
		this.step = step;
	}
	
}
