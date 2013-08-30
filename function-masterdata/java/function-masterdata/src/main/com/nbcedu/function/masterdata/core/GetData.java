package com.nbcedu.function.masterdata.core;

import java.util.Date;

public interface GetData {
	
	public Resource getData(Date start,Date end);
	
	public String getType();
	
}
