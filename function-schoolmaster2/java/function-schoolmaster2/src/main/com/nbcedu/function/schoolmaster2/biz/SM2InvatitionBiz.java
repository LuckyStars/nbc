package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;

public interface SM2InvatitionBiz extends BaseBiz<TSm2Invatition>{
	public PagerModel findByCreaterId(String createrId);
	public PagerModel findByInvatId(String invatId);
}
