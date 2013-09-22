package com.nbcedu.function.schoolmaster2.action;


import java.util.Date;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.data.interfaces.DataGenerator;
import com.nbcedu.function.schoolmaster2.data.util.DataContext;

public class DataAction extends BaseAction{
	
	/**要查看 显示 的数据类型*/
	private String dataType;
	private String matcher;
	private Date start;
	private Date end;
	
	public void show(){
		DataGenerator dataGen = DataContext.getContext().getbyMatcher(matcher);
		if(dataGen!=null){
			Struts2Util.renderXml(dataGen.getDataByTime(start, end));
		}
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
}
 