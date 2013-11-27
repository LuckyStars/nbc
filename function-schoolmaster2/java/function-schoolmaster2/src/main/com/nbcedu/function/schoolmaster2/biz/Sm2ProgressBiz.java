package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.vo.ProgressVo;

public interface Sm2ProgressBiz extends BaseBiz<TSm2Progress> {

	
	public List<TSm2Progress> findAllByStepId(String stepId);
	
	public List<TSm2Progress> findByProgressVo(ProgressVo p);
	
	public void modifyStep(String stepId,String progId);
	
	public List<ProgressVo> findVoByStepId(String stepId);
	/**
	 *删除工作进展，包括：赞；评论等下级目录；
	 * @param id
	 * @param subid
	 * @jonathan
	 */
	public void removeById1(String id,String subId);
	/**
	 * 增加工作进展同时修改master的flag状态为修改，如果为插红旗则不修改
	 * @param pro
	 * @param subjectId
	 * @return 
	 * @author jonathan
	 */
	public boolean addPro(TSm2Progress pro,String subjectId);
}
