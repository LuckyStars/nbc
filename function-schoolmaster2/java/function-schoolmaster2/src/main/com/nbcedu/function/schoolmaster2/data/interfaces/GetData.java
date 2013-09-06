package com.nbcedu.function.schoolmaster2.data.interfaces;

import java.util.Date;

public interface GetData <T extends ChartData>{
	T getDataByTime(Date start ,Date end);
}
