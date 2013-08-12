package com.nbcedu.function.teachersignup.biz;

import java.util.Collection;

import com.nbcedu.function.teachersignup.core.biz.BaseBiz;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.model.TSUserPrivilege;

public interface TSUserPrivilegeBiz extends BaseBiz<TSUserPrivilege> {
	PagerModel findAllByPage();
	
	TSUserPrivilege findByUid(String uid);
	
	void addByUid(Collection<String> uids);
}
