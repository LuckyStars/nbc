package com.nbcedu.function.schoolmaster2.biz;


import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;

public interface SM2ModuleBiz extends BaseBiz<TSm2Module>{
	/**
	 * 查询类型根据moduleId
	 * @param moduleId
	 * @return
	 */
	public List<TSm2Module> findByModuleId(String moduleId);
}
