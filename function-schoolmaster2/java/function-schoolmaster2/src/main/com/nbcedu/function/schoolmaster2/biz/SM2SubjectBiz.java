package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

public interface SM2SubjectBiz extends BaseBiz<TSm2Subject>{
	/**
	 * 分页查找主题
	 * @param subject
	 * @return
	 */
	public PagerModel findByModel(TSm2Subject subject);
	
	public PagerModel findByCreaterId(String createrId,String moduleId);
	
	public PagerModel findByExceuteUserId(String userId,String moduleId);
	
	public PagerModel findByModule(String moduleId);
	
	public List<TSm2Subject> findBYModuleId(String moduleId);
	
}
