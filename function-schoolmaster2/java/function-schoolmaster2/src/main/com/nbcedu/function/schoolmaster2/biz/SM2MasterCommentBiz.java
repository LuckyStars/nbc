package com.nbcedu.function.schoolmaster2.biz;


import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterComment;

public interface SM2MasterCommentBiz extends BaseBiz<TSm2MasterComment>{
	public List<TSm2MasterComment> findByInvatitionId(String invatitionId);

	public void removeByProgId(String progId);
}
