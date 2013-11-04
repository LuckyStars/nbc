package com.nbcedu.function.schoolmaster2.data.vo;

import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;

@SuppressWarnings("serial")
public class ProgressVo extends TSm2Progress{

	private Integer zanCount;
	private Integer readCount;
	public Integer getZanCount() {
		return zanCount;
	}
	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	
}
