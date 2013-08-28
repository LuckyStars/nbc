package com.nbcedu.function.cardmanage.biz.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMUserPrivilegeDao;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.cardmanage.util.UcService;

public class CMUserPrivilegeBizImpl extends BaseBizImpl<CMUserPrivilege> implements CMUserPrivilegeBiz{
	private CMUserPrivilegeDao cmUserPrivilegeDao;

	
	
	public void setCmUserPrivilegeDao(CMUserPrivilegeDao cmUserPrivilegeDao) {
		super.setDao(cmUserPrivilegeDao);
		this.cmUserPrivilegeDao = cmUserPrivilegeDao;
	}
	
	@Override
	public CMUserPrivilege findByUid(String uid) {
		return this.cmUserPrivilegeDao.findUniqueBy("userUid", uid);
	}

	@Override
	public void addByUid(Collection<String> uids) {
		Set  uniqueResult = new  HashSet(uids);
		Object [] s = uniqueResult.toArray();
		if(uniqueResult!=null&&!uniqueResult.isEmpty()){
			this.cmUserPrivilegeDao.removeAll();
			for (int i = 0; i<s.length;i++) {
				CMUserPrivilege pri = new CMUserPrivilege();
				pri.setUserUid(s[i].toString());
				pri.setUserName(UcService.findUserNameByUid(s[i].toString()));
				this.add(pri);
			}
		}
	}
	
}
