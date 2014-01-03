package com.nbcedu.function.schoolmaster2.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Resource;

@SuppressWarnings("serial")
public class ResourceAction extends BaseAction{
	
	String progId;
	private int type;

	SM2ResourceBiz resourceBiz;

	public String findAll(){
		this.pm = this.resourceBiz.findResource(progId,type);
		return LIST;
	}

	public String findPic(){
		List<TSm2Resource> list = this.resourceBiz.findAllResource(progId,type);
		this.getRequest().setAttribute("list", list);
		return "resource_pic";
	} 
	
	@SuppressWarnings("unchecked")
	public void add(){
		TSm2Resource res;
		String paths = this.getRequest().getParameter("resourses");
		List<TSm2Resource> list = new ArrayList<TSm2Resource>();
		for(String p : paths.split(",")){
			res = new TSm2Resource();
			res.setCreaterId(this.getUserId());
			res.setCreateTime(new Date());
			res.setFilePath(p);
			res.setFileName(p.substring(p.lastIndexOf("/")+1));
			res.setProgressId(progId);
			res.setType(type);
			list.add(res);
		}
		this.resourceBiz.addAll(list);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
	}
	
	public void delete(){
		this.resourceBiz.removeById(id);
	}
	
	/////////////////////
	//getter setter
	//////////////////
	public void setResourceBiz(SM2ResourceBiz resourceBiz) {
		this.resourceBiz = resourceBiz;
	}
	public String getProgId() {
		return progId;
	}
	public void setProgId(String progId) {
		this.progId = progId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
