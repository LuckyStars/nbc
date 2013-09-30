package com.nbcedu.function.schoolmaster2.action;


import java.util.Date;

import com.nbcedu.function.schoolmaster2.biz.SM2DataBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.interfaces.DataGenerator;
import com.nbcedu.function.schoolmaster2.data.model.SM2Datas;
import com.nbcedu.function.schoolmaster2.data.util.DataContext;

@SuppressWarnings("serial")
public class DataAction extends BaseAction{
	
	/**要查看 显示 的数据类型*/
	private String dataType;
	private String matcher;
	private Date start;
	private Date end;
	private SM2Datas data = new SM2Datas();
	private String xmlContent;
	private SM2DataBiz sm2DataBiz;
	
	public String listStatistics(){
		this.data.setStartDate(start);
		this.data.setEndDate(end);
		this.data.setMatcher(matcher);
		this.pm=this.sm2DataBiz.findPageByModel(data);
		return "listStatistic";
	}
	public String listMasterStatistics(){
		this.data.setStartDate(start);
		this.data.setEndDate(end);
		this.data.setMatcher(matcher);
		this.pm=this.sm2DataBiz.findPageByModel(data);
		return "listMasterStatistic";
	}
	public String toChart(){
		return "toChart";
	}
	public String toMasterChart(){
		this.data = this.sm2DataBiz.findById(id);
		return "toMasterChart";
	}
	public void show(){
		DataGenerator dataGen = DataContext.getContext().getbyMatcher(matcher);
		if(dataGen!=null){
			Struts2Utils.renderXml(dataGen.getDataByTime(start, end));
		}
	}

	public String chart(){
		DataGenerator dataGen = 
			DataContext.getContext().getbyMatcher(matcher);
		this.dataType = dataGen.defaultChartType();
		this.xmlContent = dataGen.getDataByTime(start, end);
		return "chart";
	}
	
	public String add(){
		this.data.setLastUpdate(new Date());
		this.data.setCreateDate(new Date());
		this.data.setStatus(0);
		this.data.setCreatorUid(this.getUserId());
		this.sm2DataBiz.add(data);
		return "refreshTeacherList";
	}
	
	public String listByType(){
		this.pm = this.sm2DataBiz.findPageByMatcher(matcher);
		return "listStatistic";
	}
	
	public String remove(){
		this.sm2DataBiz.removeById(this.id);
		return "refreshTeacherList";
	}
	
	public String modify(){
		this.data.setLastUpdate(new Date());
		this.sm2DataBiz.modify(this.data);
		return "refreshTeacherList";
	}
	
	/////////////////////////
	/////getters&setters/////
	/////////////////////////
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMatcher() {
		return matcher;
	}
	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public SM2Datas getData() {
		return data;
	}
	public void setData(SM2Datas data) {
		this.data = data;
	}
	public void setSm2DataBiz(SM2DataBiz sm2DataBiz) {
		this.sm2DataBiz = sm2DataBiz;
	}
	public String getXmlContent() {
		return xmlContent;
	}
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}
}
 