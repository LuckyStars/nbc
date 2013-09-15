/*
 * @Title: TreeNodeBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: TreeNodeBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-10 下午09:10:36
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-10                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.documentflow.biz.TreeNodeBiz;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTreeNode;

/** 
 * <p>TreeNodeBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-10 下午09:10:36
 */
public class TreeNodeBizImpl implements TreeNodeBiz {
	
	/**
	 * UC客户端查询接口对象
	 */
	private BaseClient client;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.TreeNodeBiz#findAllNodes(java.util.List)
	 */
	@Override
	public List<Map<String, Object>> findAllNodes(List<String> excepted) {
		List<Map<String, Object>> nodeJsonArray = new ArrayList<Map<String, Object>>();
		client = new BaseClient();
		List<NbcUcTreeNode> nodeList = client.queryDepartTree("root", 1);
		
		for (NbcUcTreeNode node : nodeList) {
			Map<String, Object> nodeJsonObj = new HashMap<String, Object>();
			String nodeId = node.getId().substring(node.getId().indexOf("|") + 1);
			if (excepted.size() > 0) {
				if (excepted.contains(nodeId)) {
					continue;
				}
			}
			nodeJsonObj.put("id", node.getId()); //nodeId
			nodeJsonObj.put("text", node.getTitle());
			//如果含有子节点
			//if (!node.isLeaf()) {
				nodeJsonObj.put("children", buildSubNodes(node, excepted));
			//}
			
			nodeJsonArray.add(nodeJsonObj);
		}
		
		return nodeJsonArray;
	}
	
	/** 
	 * 递归构造树节点
	 * 
	 * @param node 当前添加的树节点
	 * @param excepted 需排除的节点
	 * @return 符合JSON格式的列表
	 */ 
	private List<Map<String, Object>> buildSubNodes(NbcUcTreeNode node, List<String> excepted) {
		List<NbcUcTreeNode> subNodeList = client.queryDepartTree(node.getId(), 1);
		List<Map<String, Object>> subNodeJsonArray = new ArrayList<Map<String, Object>>();
		
		if(subNodeList!=null && subNodeList.size()>0){
			for (NbcUcTreeNode subNode : subNodeList) {
				Map<String, Object> subNodeJsonObj = new HashMap<String, Object>();
				String subNodeId = subNode.getId().substring(subNode.getId().indexOf("|") + 1);
				if (excepted.size() > 0) {
					if (excepted.contains(subNodeId)) {
						continue;
					}
				}
				
				subNodeJsonObj.put("id", subNode.getId()); //subNodeId
				subNodeJsonObj.put("text", subNode.getTitle());
				//if (!subNode.isLeaf()) {
				subNodeJsonObj.put("children", buildSubNodes(subNode, excepted));
				//}
				subNodeJsonArray.add(subNodeJsonObj);
			}
		}
		
		return subNodeJsonArray;
	}
}
