/*
 * @Title: TreeNodeBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: 组织结构树的业务逻辑接口，该接口包含了操作组织结构树的方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-10 下午09:04:44
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-10                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.List;
import java.util.Map;

/** 
 * <p>组织结构树的业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-10 下午09:04:44
 */
public interface TreeNodeBiz {
	/** 
	 * 返回全部节点
	 * 
	 * @param excepted 需排除的节点
	 * @return 全部节点的列表
	 */ 
	List<Map<String, Object>> findAllNodes(List<String> excepted);
}
