package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.SM2Reads;

public interface Sm2ReadsBiz extends BaseBiz<SM2Reads> {
	public void addByStep(String stepId,String userId);
}
