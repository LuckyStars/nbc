package com.nbcedu.function.teachersignup.biz;

import com.nbcedu.function.teachersignup.core.biz.BaseBiz;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.vo.TSUser;

public interface TSSignBiz extends BaseBiz<TSSign>{

	void addNewSign(TSUser tsUser, String[] subjectIds);

}
