package com.nbcedu.function.documentflow.biz.impl;

import java.util.List;
import com.nbcedu.function.documentflow.biz.DocumentUserRoleBiz;
import com.nbcedu.function.documentflow.model.DoucmentUserRole;

@SuppressWarnings("unchecked")
public class DocumentUserRoleBizImpl extends DocumentFlowBaseBizImpl implements DocumentUserRoleBiz {

	@Override
	public void addUserRole(DoucmentUserRole userRole) {
		getHibernateDao().create(userRole);
	}

	@Override
	public List<DoucmentUserRole> findUserRole(String pid) {
		return (List<DoucmentUserRole>)getHibernateDao().retrieve("from DoucmentUserRole ur where ur.pid=?", new Object[] {pid});
	}

	@Override
	public List<DoucmentUserRole> findUserRoles(String roleName) {
		return (List<DoucmentUserRole>) getHibernateDao().retrieve("from DoucmentUserRole ur where ur.roleName=?", new Object[] {roleName});
	}

	@Override
	public void removeUserRole(String roleName) {
		getHibernateDao().delete("delete from DoucmentUserRole ur where ur.roleName=?", new Object[] {roleName});
	}

}
