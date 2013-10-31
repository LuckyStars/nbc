package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.utils.Utils;

/**
 * 阅读vo
 * @author xuechong
 */
public class ReadsVo {
	
	private final String name;
	private final String time;
	private final boolean trans;
	
	public ReadsVo(String name, Date time, Integer trans) {
		super();
		this.name = name;
		this.time = Utils.Dates.formateFullDateStr(time);
		this.trans = trans!=null&&trans==1;
	}
	/////////////////////////
	/////getters&setters/////
	/////////////////////////
	public String getName() {
		return name;
	}
	public String getTime() {
		return time;
	}
	public boolean getTrans() {
		return trans;
	}
}
