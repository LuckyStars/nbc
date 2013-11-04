package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;

public class MasterSubSearchVO {
	
	private String moduleId;
	private String createrName;
	private Date start;
	private Date end;
	private String departId;
	private String typeId;
	private Integer flag;
	private String receiverUid;
	
	//////////////////////
	////getters&setters////
	////////////////////////
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getReceiverUid() {
		return receiverUid;
	}
	public void setReceiverUid(String receiverUid) {
		this.receiverUid = receiverUid;
	}
	
}
