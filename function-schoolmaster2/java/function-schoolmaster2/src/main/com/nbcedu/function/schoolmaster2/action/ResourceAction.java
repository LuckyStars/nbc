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
	SM2ResourceBiz resourceBiz;
	String progId;
	private int type;

	public String findAll(){
		pm = this.resourceBiz.findResource(progId,type);
		return LIST;
	}
//	public String findPic(){
//		pm = this.resourceBiz.findResource(progId,type);
//		return "resource_pic";
//	}
	public String findPic(){
		List<TSm2Resource> list = this.resourceBiz.findAllResource(progId,type);
		this.getRequest().setAttribute("list", list);
		return "resource_pic";
	} 
	public void add(){
		TSm2Resource r;
		String paths = this.getRequest().getParameter("resourses");
		List<TSm2Resource> list = new ArrayList<TSm2Resource>();
		for(String p : paths.split(",")){
			r = new TSm2Resource();
			r.setCreaterId(this.getUserId());
			r.setCreateTime(new Date());
			r.setFilePath(p);
			r.setFileName(p.substring(p.lastIndexOf("/")+1));
			r.setProgressId(progId);
			r.setType(type);
			list.add(r);
		}
		this.resourceBiz.addAll(list);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
	}
	public void delete(){
		this.resourceBiz.removeById(id);
	}
	//getter setter
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
