package com.nbcedu.function.schoolmaster2.data.vo;

import static org.apache.commons.lang.xwork.StringUtils.isBlank;
import static org.apache.commons.lang.xwork.StringUtils.trimToEmpty;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

public class SingleCharts {
	
	private static final Logger logger = Logger.getLogger(SingleCharts.class);
	
	private static final String chartXml = 
			"<graph caption='${caption}' baseFontSize='20' bgColor='${bgColor}'" +
				" xAxisName='${xAxisName}' " +
				"yAxisName='${yAxisName}' numberPrefix='${numberPrefix}' showValues='0'>"+
				"${sets}"+
			"</graph>";
	
	private static final String setXml = "<set name='${label}' value='${value}' />";
	
	private String caption;
	private String subcaption;
	private String xAxisName;
	private String yAxisName;
	private String numberPrefix;
	private String bgColor;
	
	private List<DataSet> datas;
	
	public static class DataSet{
		
		private String name;
		private String value;
		public String getName() {
			return trimToEmpty(name);
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return trimToEmpty(value);
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public String toXmlString(){
		StringBuilder setStr = new StringBuilder("");
		
		if(!CollectionUtils.isEmpty(datas)){
			for (DataSet data : datas) {
				setStr.append(
						(setXml.replace("${label}", data.getName())
								.replace("${value}",data.getValue()))
						);
			}
		}
		
		return (chartXml.replace("${caption}", this.getCaption())
				.replace("${xAxisName}", getxAxisName())
				.replace("${yAxisName}", getyAxisName())
				.replace("${numberPrefix}", getNumberPrefix())
				.replace("${sets}", setStr.toString())
				.replace("${bgColor}", getBgColor()).toString());
		
	}
	
	
	private boolean validated(){
		if(isBlank(this.getxAxisName())){
			logger.error("");
		}
		
		return Boolean.TRUE;
	}
	
	////////////////////
	///////get&set//////
	////////////////////
	public String getBgColor() {
		return bgColor ==null || bgColor.trim()=="" ?"FFFFFF":bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	public String getCaption() {
		return trimToEmpty(caption);
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSubcaption() {
		return trimToEmpty(subcaption);
	}
	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}
	public String getxAxisName() {
		return trimToEmpty(xAxisName);
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	public String getyAxisName() {
		return trimToEmpty(yAxisName);
	}
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	public String getNumberPrefix() {
		return trimToEmpty(numberPrefix);
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
