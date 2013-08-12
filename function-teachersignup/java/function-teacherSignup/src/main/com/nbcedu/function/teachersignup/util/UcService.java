package com.nbcedu.function.teachersignup.util;

import java.util.Collection;
import java.util.List;

import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTreeNode;

/**
 * 用户中心的查询
 * @author xuechong
 */
public class UcService {
	private static final BaseClient client = new BaseClient();


	public static String getPersonJson(final Collection<String> checkedUids){
		
		class CloumnTree {
			
			StringBuilder jsonString = new StringBuilder();
			
			public String getCloumnTreeXmlString() {
				jsonString.append("[");
				List<NbcUcTreeNode> list = getTreeNode("root");
				for (NbcUcTreeNode nbcUcTreeNode : list) {
					disk_NbcUcTreeNode(nbcUcTreeNode);
				}
				jsonString.append("]");
				return jsonString.toString();
			}

			private void disk_NbcUcTreeNode(NbcUcTreeNode treeNode) {
				List<NbcUcTreeNode> list = getTreeNode(treeNode.getId());
				jsonString.append("{");
				jsonString.append("\"id\":");
				jsonString.append("\"");
				jsonString.append(treeNode.getId().replace("u|", ""));
				jsonString.append("\"");
				jsonString.append(",\"text\":");
				jsonString.append("\"");
				jsonString.append(treeNode.getTitle());
				jsonString.append("\"");
				if(Checked(treeNode.getId().replace("u|", ""))){
					jsonString.append(",\"checked\":true");
				}
				if(list!=null && list.size()>0){
					jsonString.append(",\"children\":[");
					for (NbcUcTreeNode tNode : list) {
						disk_NbcUcTreeNode(tNode);
					}
					jsonString.append("]");
				}
				jsonString.append("},");
			}

			List<NbcUcTreeNode> getTreeNode(String pid) {
				List<NbcUcTreeNode> list = client.queryDepartTree(pid, 3);
				return list;
			}
			
			boolean Checked(String id){
				return checkedUids!=null
					&&!checkedUids.isEmpty()
					&& checkedUids.contains(id);
			}
			
		}
		
		String userJson = new CloumnTree().getCloumnTreeXmlString();
		if(userJson!=null){
			userJson=userJson.replaceAll(",]","]");
		}
		return userJson;
		
	}
	
	
	/**
	 * 教师组织机构树
	 * @return
	 * @author xuechong
	 */
	public static String getPersonJsonString() {
		return getPersonJson(null);
	}
	
	
	public static String findUserNameByUid(String uid){
		return client.queryPerson(1, uid).getName();
	}
}
