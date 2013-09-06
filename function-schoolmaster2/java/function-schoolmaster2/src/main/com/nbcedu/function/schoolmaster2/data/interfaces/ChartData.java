package com.nbcedu.function.schoolmaster2.data.interfaces;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class ChartData implements Serializable {
	
	protected String id;
	protected String comment;
	protected Date createDate;
	protected String title;
	protected Date startDate;
	protected Date endDate;
	
	public abstract String getXmlString();
	public abstract ChartType getChartType();
	
	
	///////////////////////////////
	////getters & setters //////
	//////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
