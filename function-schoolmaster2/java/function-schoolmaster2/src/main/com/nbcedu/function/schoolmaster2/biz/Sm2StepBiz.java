package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

public interface Sm2StepBiz extends BaseBiz<TSm2Step>{
	public boolean findByName(String name);
	
	public List<StepVo> findBySubId(String subjectId);
	
	/**
	 * 根据stepId获取同一个subject下所有step
	 * @param stepId
	 * @return
	 * @author xuechong
	 */
	public List<StepVo> findByStepId(String stepId);
	
	/**
	 * 根据progId查询相同subject下所有step
	 * @param progId
	 * @return
	 * @author xuechong
	 */
	public List<StepVo> findByProgId(String progId);
	
	
	public void delete(String id);
}
