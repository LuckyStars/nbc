package com.nbcedu.function.teachersignup.biz.impl;

import java.util.Collection;

import com.nbcedu.function.teachersignup.biz.TSUserPrivilegeBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.dao.TSUserPrivilegeDao;
import com.nbcedu.function.teachersignup.model.TSUserPrivilege;
import com.nbcedu.function.teachersignup.util.UcService;

public class TSUserPrivilegeBizImpl extends BaseBizImpl	<TSUserPrivilege> implements TSUserPrivilegeBiz{

	private TSUserPrivilegeDao userPriDao ;
	
	@Override
	public PagerModel findAllByPage() {
		return this.userPriDao.searchPaginated("FROM TSUserPrivilege");
	}

	public void setUserPriDao(TSUserPrivilegeDao userPriDao) {
		super.setDao(userPriDao);
		this.userPriDao = userPriDao;
	}

	@Override
	public TSUserPrivilege findByUid(String uid) {
		return this.userPriDao.findUniqueBy("userUid", uid);
	}

	@Override
	public void addByUid(Collection<String> uids) {
		if(uids!=null&&!uids.isEmpty()){
			this.userPriDao.removeAll();
			for (String uid : uids) {
				TSUserPrivilege pri = new TSUserPrivilege();
				pri.setUserUid(uid);
				pri.setUserName(UcService.findUserNameByUid(uid));
				this.add(pri);
			}
		}
	}
	
}
