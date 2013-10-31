package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.SM2Reads;
import com.nbcedu.function.schoolmaster2.vo.ReadsVo;

public interface Sm2ReadsBiz extends BaseBiz<SM2Reads> {
	
	public void addByStep(String stepId,String userId);
	
	public List<ReadsVo> findByProg(String progId,Integer size);
}
