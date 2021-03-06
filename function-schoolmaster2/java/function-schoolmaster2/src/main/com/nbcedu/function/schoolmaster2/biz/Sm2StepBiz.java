package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

public interface Sm2StepBiz extends BaseBiz<TSm2Step>{
	/**
	 * 查找重名
	 * @param name
	 * @param id
	 * @return jonathan
	 */
	public boolean findByName(String name,String id);
	/**
	 * 根据主题id查询所有step
	 * @param subjectId
	 * @return jonathan
	 */
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
	/**
	 * 删除
	 * @param id
	 * @return jonathan
	 */
	public boolean removeById1(String id);
	/**
	 * 修改 step同时修改审批人的查询状态flag为修改
	 * @param name
	 * @param subId
	 * @return jonathan
	 */
	boolean updateBySubId(TSm2Step step);
	/**
	 * 增加工作步骤同时修改flag为修改
	 * @param step
	 */
	boolean addStep(TSm2Step step);
	
	
}
