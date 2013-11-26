package com.nbcedu.function.schoolmaster2.biz;

import java.util.Collection;
import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Comment;

public interface SM2CommentBiz extends BaseBiz<TSm2Comment> {
	
	public List<TSm2Comment> findByProgIds(Collection<String> progIds,Integer size);
	public List<TSm2Comment> findByProgIds(Collection<String> progIds);
	public void removeByProgId(String progId);
}
