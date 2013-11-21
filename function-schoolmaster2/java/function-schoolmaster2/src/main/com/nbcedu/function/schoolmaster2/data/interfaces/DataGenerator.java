package com.nbcedu.function.schoolmaster2.data.interfaces;

import java.util.Date;

public interface DataGenerator{
	String getDataByTime(Date start ,Date end);
	String getDataByTime(Date start ,Date end,String color);
	String defaultChartType();
}
