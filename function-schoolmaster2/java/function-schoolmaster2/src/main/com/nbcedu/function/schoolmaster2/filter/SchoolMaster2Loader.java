package com.nbcedu.function.schoolmaster2.filter;

import java.util.Map;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;

public class SchoolMaster2Loader implements ServiceInfoLoader{

	@Override
	public Object load(Map<?, ?> params) {
		
		return params.get("uid");
	}

}
