package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.utils.Utils;

public class ZanVo {

	private final String name;
	private final String date;
	public ZanVo(String userName, Date date) {
		super();
		this.name = userName;
		this.date = Utils.Dates.formateFullDateStr(date);
	}
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
}
