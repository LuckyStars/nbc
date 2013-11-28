package com.nbcedu.function.schoolmaster2.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SubWeekSearch {

	private List<Integer> status = new LinkedList<Integer>();
	private Date start;
	private Date updateDate;
	private List<String> publisher = new LinkedList<String>();
	private List<String> subType = new LinkedList<String>();
	
	//////////////////////////
	/////getters&setters///////
	/////////////////////////
	public List<Integer> getStatus() {
		return status;
	}
	public void setStatus(List<Integer> status) {
		this.status = status;
	}
	public void setStatus(Integer[] status) {
		this.status = Arrays.asList(status);
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
	public void setPublisher(String[] publisher) {
		this.publisher = Arrays.asList(publisher);
	}
	public List<String> getSubType() {
		return subType;
	}
	public void setSubType(List<String> subType) {
		this.subType = subType;
	}
	public void setSubType(String[] subType) {
		this.subType = Arrays.asList(subType);
	}
	
	
}
