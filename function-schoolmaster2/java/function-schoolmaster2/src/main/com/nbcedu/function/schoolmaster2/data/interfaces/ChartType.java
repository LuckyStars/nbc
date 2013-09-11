package com.nbcedu.function.schoolmaster2.data.interfaces;

/**
 * 视图类型
 * @author xuechong
 *
 */
public enum ChartType {
	
	Area2D(1,"Area2D"),
	Bar2D(2,"Bar2D"),
	Candlestick(3,"Candlestick");
	
	private final String name;
	private final int id;
	
	private ChartType(int id,String name){
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	
}
