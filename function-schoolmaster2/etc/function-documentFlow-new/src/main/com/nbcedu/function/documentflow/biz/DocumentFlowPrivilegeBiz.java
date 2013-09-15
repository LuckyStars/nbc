/*
 * @Title: DocumentFlowPrivilegeBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: 公文权限设置接口，该接口包含了操作公文权限的业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-11-29 下午06:02:53
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-11-29                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.List;

import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;

/** 
 * <p>公文权限设置接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-11-29 下午06:02:53
 */
public interface DocumentFlowPrivilegeBiz extends DocumentFlowBaseBiz {
	/** 
	 * 返回公文管理员列表
	 * 
	 * @return 公文管理员列表
	 */ 
	List<User> findDocumentFlowAdmins();
	/** 
	 * 修改权限
	 * 
	 * @param ptype 要修改的权限类型
	 * @param pids 要设置为该权限类型的用户PID的数组
	 */ 
	void modifyPrivilege(String ptype, String[] pids);
	
	/**
	 * 按照权限名称查询用户
	 * @param roleName
	 * @return
	 * @author xuechong
	 */
	List<User> findUserByRoleName(String roleName);
	
	/**
	 * 查询角色名称
	 * @param roleName
	 * @return
	 * @author xuechong
	 */
	public Role findDocumentRoleByName(String roleName);
}
