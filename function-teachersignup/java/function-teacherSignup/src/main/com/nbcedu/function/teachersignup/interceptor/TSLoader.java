package com.nbcedu.function.teachersignup.interceptor;

import java.util.Map;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;

public class TSLoader implements ServiceInfoLoader{
	
	@Override
	public Object load(Map<?, ?> paramMap) {
		return (String) paramMap.get("uid");
	}
}
