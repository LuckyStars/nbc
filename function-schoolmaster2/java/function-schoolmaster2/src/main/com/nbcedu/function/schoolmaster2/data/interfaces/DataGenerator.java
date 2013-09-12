package com.nbcedu.function.schoolmaster2.data.interfaces;

import java.util.Date;

public interface DataGenerator <T extends ChartData>{
	
	T getDataByTime(Date start ,Date end);
	
	boolean match(String flag);
}
