package com.nbcedu.function.masterdata.core;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class Resource implements Serializable {
	
	protected String id;
	protected String xml;
	protected String content;
	protected String type;
	protected Date startDate;
	protected Date endDate;
	protected String title;
	
	///////////////////////
	////getters setters////
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
