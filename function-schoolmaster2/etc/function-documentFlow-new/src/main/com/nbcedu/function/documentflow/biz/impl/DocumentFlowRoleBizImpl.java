package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;
import java.util.List;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.core.privilege.biz.RoleBiz;
import com.nbcedu.core.privilege.model.Role;

public class DocumentFlowRoleBizImpl extends DocumentFlowBaseBizImpl implements RoleBiz {

	@Override
	public void addRole(Role role) {
		
	}

	@Override
	public Role findRole(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findRoleByName(String name) {
		// TODO 111
		return null;
	}

	@Override
	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRoles(String queryString, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRoles(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRolesByFunctionName(String functionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyRole(Role role) {
		// TODO 更新userList
		
		
	}

	@Override
	public void modifyRole(String queryString, Object[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRole(Role role) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeRole(Serializable id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Integer removeRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer removeRoles(String queryString, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findPagedResults(String queryString, Object[] params, PagerUtils pagerUtils) {
		return null;
	}

}
