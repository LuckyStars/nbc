package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SubWeekSearch {

	private List<String> status = new LinkedList<String>();
	private Date start;
	private Date updateDate;
	private List<String> publisher = new LinkedList<String>();
	private List<String> subType = new LinkedList<String>();
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<String> getPublisher() {
		return publisher;
	}
	public void setPublisher(List<String> publisher) {
		this.publisher = publisher;
	}
	public List<String> getSubType() {
		return subType;
	}
	public void setSubType(List<String> subType) {
		this.subType = subType;
	}

	
	
}
