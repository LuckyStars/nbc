package com.nbcedu.function.schoolmaster2.data.vo;

import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;

@SuppressWarnings("serial")
public class ProgressVo extends TSm2Progress{

	private Integer zanCount;
	private Integer readCount;
	/***
	 * 是否本人已赞过
	 */
	private Integer zand;
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
	public Integer getZand() {
		return zand;
	}
	public void setZand(Integer zand) {
		this.zand = zand;
	}
	
}
