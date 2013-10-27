package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;

public interface SM2MasterSubBiz extends SM2SubjectBiz {

	/**
	 * 按校长和模块类型分页
	 * @param modId
	 * @param masterUid
	 * @return
	 * @author xuechong
	 */
	public PagerModel findByMaster(String modId,String masterUid);
}
