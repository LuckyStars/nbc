package com.nbcedu.function.schoolmaster2.biz;


import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;

public interface Sm2TypeBiz extends BaseBiz<TSm2Type>{
	/**
	 * 查询类型根据moduleId
	 * @param moduleId
	 * @return
	 */
	public List<TSm2Type> findByModUseId(String moduleId,String UserId);
}
