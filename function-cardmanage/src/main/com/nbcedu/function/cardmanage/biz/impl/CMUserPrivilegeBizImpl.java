package com.nbcedu.function.cardmanage.biz.impl;

import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMUserPrivilegeDao;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;

public class CMUserPrivilegeBizImpl extends BaseBizImpl<CMUserPrivilege> implements CMUserPrivilegeBiz{
	private CMUserPrivilegeDao cmUserPrivilegeDao;

	
	
	public void setCmUserPrivilegeDao(CMUserPrivilegeDao cmUserPrivilegeDao) {
		super.setDao(cmUserPrivilegeDao);
		this.cmUserPrivilegeDao = cmUserPrivilegeDao;
	}
	
}
