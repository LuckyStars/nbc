package com.nbcedu.function.schoolmaster2.data.interfaces;

import org.apache.commons.lang.xwork.StringUtils;

public abstract class AbstractDataGenerator<T extends ChartData> implements DataGenerator<T>{
	
	protected ChartType chartType;
	
	protected String matchStr;
	
	protected abstract String getMatchStr();
	
	@Override
	public boolean match(String flag) {
		return StringUtils.isNotBlank(flag) 
			&& StringUtils.isNotBlank(getMatchStr())
			&&getMatchStr().equals(flag);
	}
	
}
