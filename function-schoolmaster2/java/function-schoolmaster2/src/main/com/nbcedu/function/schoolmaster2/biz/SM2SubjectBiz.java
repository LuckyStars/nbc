package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

public interface SM2SubjectBiz extends BaseBiz<TSm2Subject>{
	/**
	 * 增加主题
	 */
	public TSm2Subject add(TSm2Subject subject);
	/**
	 * 分页查找主题
	 * @param subject
	 * @return
	 */
	public PagerModel findByModel(TSm2Subject subject);
}
