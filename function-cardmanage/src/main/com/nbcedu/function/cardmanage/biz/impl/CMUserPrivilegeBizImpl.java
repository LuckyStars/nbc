package com.nbcedu.function.cardmanage.biz.impl;

import java.util.Collection;

import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.cardmanage.dao.CMUserPrivilegeDao;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.teachersignup.model.TSUserPrivilege;
import com.nbcedu.function.teachersignup.util.UcService;

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
		if(uids!=null&&!uids.isEmpty()){
			this.cmUserPrivilegeDao.removeAll();
			for (String uid : uids) {
				CMUserPrivilege pri = new CMUserPrivilege();
				pri.setUserUid(uid);
				pri.setUserName(UcService.findUserNameByUid(uid));
				this.add(pri);
			}
		}
	}
	
}
