package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.vo.ProgressVo;

public interface Sm2ProgressBiz extends BaseBiz<TSm2Progress> {

	
	public List<TSm2Progress> findAllByStepId(String stepId);
	
	public List<TSm2Progress> findByNameStepId(String stepId,String name);
	
	public void modifyStep(String stepId,String progId);
	
	public List<ProgressVo> findVoByStepId(String stepId);
	/**
	 *删除工作进展，包括：赞；转发；
	 * @param id
	 */
	public void deleteById(String id);
}
