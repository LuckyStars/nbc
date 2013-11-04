package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

public interface SM2SubjectBiz extends BaseBiz<TSm2Subject>{
	/**
	 * 分页查找主题
	 * @param subject
	 * @return
	 */
	public PagerModel findByModel(TSm2Subject subject);
	
	public PagerModel findByCreaterId(SubjectVo subject);
	
	public PagerModel findByExceuteUserId(SubjectVo subject);
	
	public PagerModel findByModule(String moduleId);
	
	public List<TSm2Subject> findBYModuleId(String moduleId);
	
	public List<TSm2Subject> findByModuleIdExceuteUserId(String moduleId,String exceuteUserId);
	
	public void update(TSm2Subject subject);
	/**
	 * 根据校长查询subject列表
	 * @param subject
	 * @return
	 */
	PagerModel findBySubjectMaster(SubjectVo subject);
	
}
