package com.nbcedu.function.schoolmaster2.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nbcedu.function.schoolmaster2.biz.SM2DisscusBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ReadsBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.utils.UCService;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

/**
 * 校长
 * @author xuechong
 */
@SuppressWarnings("serial")
public class MasterSubjectAction extends BaseAction{
	
	private String moduleId;
	private TSm2Subject subject;
	private TSm2Step step;
	private List<TSm2Progress> proList = new ArrayList<TSm2Progress>();
	private String name;
	
	private SM2MasterSubBiz subBiz;
	private Sm2StepBiz stepBiz;
	private Sm2ProgressBiz progBiz;
	private Sm2ReadsBiz readsBiz;
	private SM2DisscusBiz disscusBiz;
	
	public String list(){
		if(StringUtils.isNotBlank(moduleId)){
			if(Utils.getDefaultMasterUids().contains(this.getUserId())){
				this.pm = this.subBiz.findByModule(moduleId); 
			}else{
				this.pm = this.subBiz.findByMaster(moduleId, this.getUserId());
			}
			return "subList";
		}else{
			return "404";
		}
	}
	
	/**
	 * 查看详细
	 * @return
	 * @author xuechong
	 */
	public String detail(){
		this.subject = this.subBiz.findById(this.id);
		List<StepVo> steps = this.subBiz.findAllSteps(this.id);
		this.getRequestMap().put("steps", steps);
		return "detail";
	}
	
	/**
	 * 查看步骤
	 * @return
	 * @author xuechong
	 */
	public String showStep(){
		this.proList = this.progBiz.findAllByStepId(this.id);
		this.readsBiz.addByStep(this.id, getUserId());
		
		if(!CollectionUtils.isEmpty(proList)){
			Collection<String> progIds = Collections2.transform(
					proList, new Function<TSm2Progress, String>() {
						@Override
						public String apply(TSm2Progress input) {
							return input.getId();
						}
					});
			
			/*disCuz map*/{
				List<TSm2Disscus> disList = this.disscusBiz.findByProgIds(progIds);
				if(!CollectionUtils.isEmpty(disList)){
					
					HashMap<String, List<TSm2Disscus>> disMap = new HashMap<String, List<TSm2Disscus>>(disList.size());
					
					for (TSm2Disscus dis : disList) {
						if(!disMap.containsKey(dis.getProgressId())){
							disMap.put(dis.getProgressId(),
									new ArrayList<TSm2Disscus>());
						}
						disMap.get(dis.getProgressId()).add(dis);
					}
					
					this.getRequestMap().put("disMap", disMap);
				}
			}
			
		}
		
		
		return "stepDetail";
	}
	public void addStep(){
		this.step.setCreaterId(this.getUserId());
		this.step.setCreateTime(new Date());
		TSm2Step s = this.stepBiz.add(step);
		Struts2Utils.renderText(s.getId(),"encoding:UTF-8");
	}
	public void isExistStep(){
		boolean b = this.stepBiz.findByName(name);
		if(b){
			Struts2Utils.renderText("0","encoding:UTF-8");
		}
	}
	public void deleteStep(){
		
	}
	
	/**
	 * 转发人员树
	 * @author xuechong
	 */
	public void transTree(){
		String json = UCService.getPersonJson(Lists.newArrayList(getUserId()),false);
		Struts2Utils.renderJson(json);
	}
	
	///////////////////////
	////getters&setters////
	///////////////////////
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public void setSubBiz(SM2MasterSubBiz subBiz) {
		this.subBiz = subBiz;
	}
	public TSm2Subject getSubject() {
		return subject;
	}
	public void setSubject(TSm2Subject subject) {
		this.subject = subject;
	}
	public TSm2Step getStep() {
		return step;
	}
	public void setStep(TSm2Step step) {
		this.step = step;
	}
	public List<TSm2Progress> getProList() {
		return proList;
	}
	public void setProList(List<TSm2Progress> proList) {
		this.proList = proList;
	}
	public void setStepBiz(Sm2StepBiz stepBiz) {
		this.stepBiz = stepBiz;
	}
	public void setProgBiz(Sm2ProgressBiz progBiz) {
		this.progBiz = progBiz;
	}
	public void setReadsBiz(Sm2ReadsBiz readsBiz) {
		this.readsBiz = readsBiz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDisscusBiz(SM2DisscusBiz disscusBiz) {
		this.disscusBiz = disscusBiz;
	}
	
}
