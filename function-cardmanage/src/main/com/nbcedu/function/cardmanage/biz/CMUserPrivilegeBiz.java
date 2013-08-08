package com.nbcedu.function.cardmanage.biz;

import java.util.Collection;

import com.nbcedu.function.cardmanage.core.biz.BaseBiz;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;

public interface CMUserPrivilegeBiz extends BaseBiz<CMUserPrivilege>{
	CMUserPrivilege findByUid(String uid);
	void addByUid(Collection<String> uids);
}
