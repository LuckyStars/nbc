package com.nbcedu.function.schoolmaster2.data.vo;

import java.util.List;

public class SingleCharts {
	
	private String caption;
	private String subcaption;
	private String xAxisName;
	private String yAxisName;
	private String numberPrefix;
	
	private List<DataSet> datas;
	
	public static class DataSet{
		
		private String name;
		private String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public String toXmlString(){
		return "";
	}
	////////////////////
	///////get&set//////
	////////////////////
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSubcaption() {
		return subcaption;
	}
	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}
	public String getxAxisName() {
		return xAxisName;
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	public String getyAxisName() {
		return yAxisName;
	}
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	public String getNumberPrefix() {
		return numberPrefix;
	}
	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}
	public List<DataSet> getDatas() {
		return datas;
	}
	public void setDatas(List<DataSet> datas) {
		this.datas = datas;
	}
	
}
