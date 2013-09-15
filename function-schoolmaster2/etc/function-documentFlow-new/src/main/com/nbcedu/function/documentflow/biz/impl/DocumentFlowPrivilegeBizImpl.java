/*
 * @Title: DocumentFlowPrivilegeBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: DocumentFlowPrivilegeBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-11-29 下午06:39:08
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-11-29                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;
import com.nbcedu.function.documentflow.biz.DocumentFlowPrivilegeBiz;
import com.nbcedu.function.documentflow.biz.DocumentUserRoleBiz;
import com.nbcedu.function.documentflow.constant.RoleConstants;
import com.nbcedu.function.documentflow.model.DoucmentUserRole;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.integration.uc.client.facade.BaseClient;

/** 
 * <p>DocumentFlowPrivilegeBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-11-29 下午06:39:08
 */
public class DocumentFlowPrivilegeBizImpl extends DocumentFlowBaseBizImpl implements DocumentFlowPrivilegeBiz {
	private DocumentUserRoleBiz documentUserRoleBiz;
	private BaseClient baseClient;
	
	@Override
	public List<User> findDocumentFlowAdmins() {
		
		List<DoucmentUserRole> urList = documentUserRoleBiz.findUserRoles("DOCUMENTFLOW_ADMIN");
		
		List<User> admins = new ArrayList<User>();
		for(DoucmentUserRole ur : urList){
			User u = new User();
			//u.setUid(uid);
			u.setPid(ur.getPid());
			u.setName(ur.getPersonName());
			
			admins.add(u);
		}
		return admins;
	}
	
	//v2
	public void modifyPrivilege(String ptype, String[] pids) {
		documentUserRoleBiz.removeUserRole(ptype);
		
		if (pids!=null&&pids.length>0) {
			List<DoucmentUserRole> result = new ArrayList<DoucmentUserRole>();
			for (String pid : pids) {
				if(!StringUtil.isBlank(pid)){
					DoucmentUserRole ur = new DoucmentUserRole();
					ur.setRoleName(ptype);
					ur.setPid(pid);
					ur.setPersonName(baseClient.queryTeacher(1, pid).getName());
					result.add(ur);
				}
			}
			this.getHibernateDao().batchCreate(result, result.size());
		}
		
		if ("DOCUMENTFLOW_ADMIN".equals(ptype)) {
			DoucmentUserRole admin = new DoucmentUserRole();
			admin.setPersonName("admin");
			admin.setPid("1");
			admin.setRoleName("DOCUMENTFLOW_ADMIN");
			documentUserRoleBiz.addUserRole(admin);
		}
	}
	
	@Override
	public List<User> findUserByRoleName(String roleName) {
		List<User> result = new ArrayList<User>();
		List<DoucmentUserRole> urList = documentUserRoleBiz.findUserRoles(roleName);
		if(urList!=null && urList.size()>0){
			for(DoucmentUserRole ur : urList){
				User u = new User();
				//u.setUid(uid);
				u.setPid(ur.getPid());
				u.setName(ur.getPersonName());
				result.add(u);
			}
		}
		return result;
	}
	
	@Override
	public Role findDocumentRoleByName(String roleName) {
		
		if (roleName.equals(RoleConstants.DOCUMENTFLOW_ADMIN)
				|| roleName.equals(RoleConstants.DOCUMENTFLOW_PUBLISHER)
				|| roleName.equals(RoleConstants.DOCUMENTFLOW_TRANSFERER)
				|| roleName.equals(RoleConstants.DOCUMENTFLOW_COMMON)) {
			Role result = new Role();
			result.setName(roleName);
			return result;
		} else {
			throw new IllegalArgumentException("没有此角色名称");
		}
		
	}

	public DocumentUserRoleBiz getDocumentUserRoleBiz() {
		return documentUserRoleBiz;
	}

	public void setDocumentUserRoleBiz(DocumentUserRoleBiz documentUserRoleBiz) {
		this.documentUserRoleBiz = documentUserRoleBiz;
	}

	public BaseClient getBaseClient() {
		return baseClient;
	}

	public void setBaseClient(BaseClient baseClient) {
		this.baseClient = baseClient;
	}

}
