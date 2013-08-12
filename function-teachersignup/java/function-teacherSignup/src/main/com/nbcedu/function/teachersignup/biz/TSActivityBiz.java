package com.nbcedu.function.teachersignup.biz;

import com.nbcedu.function.teachersignup.core.biz.BaseBiz;
import com.nbcedu.function.teachersignup.model.TSActivity;

public interface TSActivityBiz extends BaseBiz<TSActivity>{
	
	public void addOrUpdate(TSActivity activity,String[] subjects,String[] rewards);
}
