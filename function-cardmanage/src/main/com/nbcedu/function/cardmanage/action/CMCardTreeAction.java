package com.nbcedu.function.cardmanage.action;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.util.Struts2Util;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.ClassStudent;
import com.nbcedu.function.cardmanage.vo.GradeClass;
import com.opensymphony.xwork2.ActionContext;

public class CMCardTreeAction extends BaseAction{
	
	@SuppressWarnings("unchecked")
	public void classTree() throws Exception {

		JSONArray jsonArray = new JSONArray();
		List<GradeClass> gradeClasseList = UcService.findAllGradeClass();
		for (GradeClass gradeClass : gradeClasseList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", gradeClass.getId());
			jsonObject.put("text", gradeClass.getName());
			jsonObject.put("state", "closed");
			List<GradeClass> gcList = gradeClass.getGradeClassList();
			if(gcList!=null){
				JSONArray subJsonArray = new JSONArray();
				for(GradeClass gc : gcList){
					JSONObject jo = new JSONObject();
					jo.put("id", gc.getId());
					jo.put("text", gc.getName());
					subJsonArray.add(jo);
				}
				jsonObject.put("children", subJsonArray);
			}
			jsonArray.add(jsonObject);
		}
		Struts2Util.renderJson(jsonArray.toJSONString(), "encoding:utf-8");
	}
	@SuppressWarnings("unchecked")
	public void studentTree() throws Exception {
		String classID = getRequest().getParameter("classID");
		JSONArray jsonArray = new JSONArray();
		List<ClassStudent> gradeClasseList = UcService.findAllClassStudent(classID);
		for (ClassStudent classStudent : gradeClasseList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", classStudent.getId());
			jsonObject.put("text", classStudent.getName());
			jsonObject.put("state", "open");
			List<ClassStudent> gcList = classStudent.getClassStudentList();
			if(gcList!=null){
				JSONArray subJsonArray = new JSONArray();
				for(ClassStudent gc : gcList){
					JSONObject jo = new JSONObject();
					jo.put("id", gc.getId());
					jo.put("text", gc.getName());
					subJsonArray.add(jo);
				}
				jsonObject.put("children", subJsonArray);
			}
			jsonArray.add(jsonObject);
		}
		Struts2Util.renderJson(jsonArray.toJSONString(), "encoding:utf-8");
	}
}
