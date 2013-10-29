package com.nbcedu.function.schoolmaster2.biz;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;

public interface SM2ZanBiz extends BaseBiz<Sm2Zan>{

	Sm2Zan findByProgUid(String progId,String uid);
}
