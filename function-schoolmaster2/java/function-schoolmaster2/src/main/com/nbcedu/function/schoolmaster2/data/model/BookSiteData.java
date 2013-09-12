/**
 * 
 */
package com.nbcedu.function.schoolmaster2.data.model;

import com.nbcedu.function.schoolmaster2.data.interfaces.ChartData;
import com.nbcedu.function.schoolmaster2.data.interfaces.ChartType;
import com.nbcedu.function.schoolmaster2.data.vo.SingleCharts;

/**
 * @author xuechong
 */
public class BookSiteData extends ChartData{

	private SingleCharts chart;
	
	public BookSiteData (SingleCharts chart){
		this.chart = chart;
	}
	
	@Override
	public String getXmlString() {
		return this.chart.toXmlString();
	}

	@Override
	public ChartType getChartType() {
		return ChartType.Bar2D;
	}

}
