package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;

public interface Sm2ProgressBiz extends BaseBiz<TSm2Progress> {

	
	public List<TSm2Progress> findAllByStepId(String stepId);
	
	public List<TSm2Progress> findByNameStepId(String stepId,String name);
}
