package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;

public interface SM2ModuleBiz extends BaseBiz<TSm2Module>{
	/**
	 * 分页查找module
	 * @param Module
	 * @return
	 */
	public PagerModel findByModel(TSm2Module module);
}
