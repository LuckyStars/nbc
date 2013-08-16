package com.nbcedu.function.cardmanage.interceptor;

import java.util.Map;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;

public class CMLoader implements ServiceInfoLoader{
	
	@Override
	public Object load(Map<?, ?> paramMap) {
		return (String) paramMap.get("uid");
	}
}
