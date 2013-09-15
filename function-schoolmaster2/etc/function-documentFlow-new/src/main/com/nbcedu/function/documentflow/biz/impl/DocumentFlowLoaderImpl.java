/*
 * @Title: DocumentFlowLoaderImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: 公文流转信息加载实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-13 下午02:43:34
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-13                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;
import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;
import com.nbcedu.function.documentflow.biz.DocumentUserRoleBiz;
import com.nbcedu.function.documentflow.model.DoucmentUserRole;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

/**
 * <p>
 * 公文流转信息加载实现类
 * </p>
 * 
 * @author Wang Zhuoxuan Create at:2011-9-13 下午02:43:34
 */
public class DocumentFlowLoaderImpl implements ServiceInfoLoader {
	private BaseClient baseClient;
	private DocumentUserRoleBiz documentUserRoleBiz;

	/**
	 * User对象填充：uid、pid、name、roleList
	 * v2
	 * @since 2012-10-17
	 */
	public Object load(Map<?, ?> arg0) {
		User user = new User();
		List<Role> roles = new ArrayList<Role>();

		String uid = (String) arg0.get("uid");
		String pid = null;
		if ("1".equals(uid)) {
			pid = "1";
		}else{
			pid = baseClient.queryUidPid(1, uid);
		}
		user.setUid(uid);
		user.setPid(pid);

		List<DoucmentUserRole> userRoleList = documentUserRoleBiz.findUserRole(pid);
		if ("1".equals(uid)) {
			Role r = new Role();
			r.setName("DOCUMENTFLOW_ADMIN");
			roles.add(r);
			user.setRoles(roles);
			user.setName("admin");

			if (userRoleList == null || userRoleList.size() <= 0) {
				DoucmentUserRole ur = new DoucmentUserRole();
				ur.setPersonName("admin");
				ur.setPid(pid);
				ur.setRoleName("DOCUMENTFLOW_ADMIN");
				documentUserRoleBiz.addUserRole(ur);
			}
		} else {
			if (userRoleList == null || userRoleList.size() <= 0) {
				NbcUcTeacher teacher = baseClient.queryTeacher(1, pid);
				user.setName(teacher.getName());

				Role r = new Role();
				r.setName("DOCUMENTFLOW_COMMON");
				roles.add(r);
				user.setRoles(roles);
			} else {
				for (DoucmentUserRole ur : userRoleList) {
					Role r = new Role();
					r.setName(ur.getRoleName());
					roles.add(r);
					
					user.setName(ur.getPersonName());
				}
				user.setRoles(roles);
			}
		}

		return user;
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
