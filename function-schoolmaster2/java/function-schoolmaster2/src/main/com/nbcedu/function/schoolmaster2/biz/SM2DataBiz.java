package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.SM2Datas;

public interface SM2DataBiz extends BaseBiz<SM2Datas>{
	PagerModel findPageByMatcher(String matcher);
}
