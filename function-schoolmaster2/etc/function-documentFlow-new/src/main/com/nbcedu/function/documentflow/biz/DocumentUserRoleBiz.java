package com.nbcedu.function.documentflow.biz;

import java.util.List;

import com.nbcedu.function.documentflow.model.DoucmentUserRole;

/**
 * 用户权限
 * 
 * @author qinyuan
 * @since 2012-10-16
 */
public interface DocumentUserRoleBiz extends DocumentFlowBaseBiz {

	/**
	 * 保存用户权限
	 */
	void addUserRole(DoucmentUserRole userRole);

	/**
	 * 根据角色类型，返回指拥有该角色的用户列表
	 */
	List<DoucmentUserRole> findUserRoles(String roleName);

	/**
	 * 根据用户pid，获取用户角色集合
	 */
	List<DoucmentUserRole> findUserRole(String pid);

	/**
	 * 根据角色类型，删除该角色的全部用户
	 */
	void removeUserRole(String roleName);
}
