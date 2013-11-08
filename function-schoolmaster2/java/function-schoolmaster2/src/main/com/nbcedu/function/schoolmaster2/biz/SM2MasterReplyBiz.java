package com.nbcedu.function.schoolmaster2.biz;


import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterReply;

public interface SM2MasterReplyBiz extends BaseBiz<TSm2MasterReply>{
	public List<TSm2MasterReply> findByCommentId(String commentId);
	
	public void removeByProgId(String progId);
}
