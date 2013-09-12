package com.nbcedu.function.schoolmaster2.filter;

import java.util.Map;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;
import com.nbcedu.function.documentflow.service.SpringBeanService;

public class SchoolMaster2Loader implements ServiceInfoLoader{

	@Override
	public Object load(Map<?, ?> params) {
		new  SpringBeanService().getBean(ServiceInfoLoader.class, "documentFlowLoader").load(params);
		return params.get("uid");
	}

}
