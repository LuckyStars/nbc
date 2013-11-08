package com.nbcedu.function.schoolmaster2.biz;


import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Resource;

public interface SM2ResourceBiz extends BaseBiz<TSm2Resource>{
	public List<TSm2Resource> findByInvatitionId(String invatitionId);
	
	public void removeByProgId(String progId);
}
