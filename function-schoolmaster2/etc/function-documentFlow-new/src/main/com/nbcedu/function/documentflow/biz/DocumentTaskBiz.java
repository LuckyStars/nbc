/*
 * @Title: DocumentTaskBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: DocumentTask实体业务逻辑接口，该接口包含了DocumentTask实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-15 下午01:31:22
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-15                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.List;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.model.DocumentTask;
import com.nbcedu.function.documentflow.vo.DocumentTaskVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.nbcedu.function.documentflow.vo.StatisticsVO;

/** 
 * <p>DocumentTask实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-15 下午01:31:22
 */
public interface DocumentTaskBiz extends DocumentFlowBaseBiz {
	/** 
	 * 新增公文处理任务
	 * 
	 * @param documentVo 公文的值对象
	 * @param type 公文任务的类型（0：原发任务；1：转发任务）
	 * @param handlingUrl 推送到门户详细页URL
	 * @return 新增公文处理任务的数量
	 */ 
	Integer addDocumentTask(DocumentVO documentVo, String type, String handlingUrl);
	/** 
	 * 返回给定条件的分页结果列表
	 * 
	 * @param queryConditionVo 给定条件的值对象
	 * @param pagerUtils 分页信息工具实体
	 * @param handlerId 处理人唯一标识
	 * @param status 公文任务的处理状态
	 * @return 公文值对象列表
	 */ 
	List<DocumentTaskVO> findDocumentTasks(QueryConditionVO queryConditionVo,
			PagerUtils pagerUtils, String handlerId, boolean status);
	/** 
	 * 返回指定公文任务的类型（0：原发任务；1：转发任务）
	 * 
	 * @param docId 所属公文的唯一标识
	 * @param handlerId 处理人的唯一标识
	 * @return 指定公文任务的类型
	 */ 
	String findDocumentTaskType(String docId, String handlerId);
	
	/** 
	 * 返回指定公文任务
	 * 
	 * @param docId 所属公文的唯一标识
	 * @param handlerId 处理人的唯一标识
	 * @return 指定公文任务
	 */ 
	DocumentTask findDocumentTaskByHanderId(String docId, String handlerId);
	/** 
	 * 返回指定公文的已处理数量
	 * 
	 * @param document 指定的公文
	 * @return 公文已处理的数量
	 */ 
	int findDocumentTaskCountHandled(Document document);
	/** 
	 * 返回指定公文的未处理数量
	 * 
	 * @param document 指定的公文
	 * @return 公文未处理的数量
	 */ 
	int findDocumentTaskCountUnHandled(Document document);
	/** 
	 * 返回给定条件的公文处理任务的列表
	 * 
	 * @param documentId 公文处理任务关联的公文唯一标识
	 * @return 符合条件的公文处理任务的列表
	 */ 
	List<DocumentTask> findDocumentTasks(String documentId);
	/** 
	 * 统计公文任务的完成情况
	 * 
	 * @param queryConditionVo 给定条件的值对象
	 * @param pagerUtils 分页信息工具实体
	 * @return 统计信息列表
	 */ 
	List<StatisticsVO> findDocumentTasksStat(QueryConditionVO queryConditionVo,
			PagerUtils pagerUtils);
	/** 
	 * 返回指定公文的未处理人员
	 * 
	 * @param documentId 指定公文的唯一标识
	 * @return 未处理人员的唯一标识列表
	 */ 
	List<String> findDocumentTaskUnhandledUsers(String documentId);
	/** 
	 * 返回指定公文的转发人员
	 * 
	 * @param documentId 指定公文的唯一标识
	 * @return 未处理人员的唯一标识列表
	 */ 
	List<String> findDocumentTaskForwardsUsers(String documentId);
	/** 
	 * 返回指定公文的转发处理人员
	 * 
	 * @param documentId 指定公文的唯一标识
	 * @return 未处理人员的唯一标识列表
	 */ 
	List<String> findDocumentTaskhandledUsersForwards(String documentId);
	/** 
	 * 修改公文任务状态为已处理
	 * 
	 * @param documentId 公文的唯一标识
	 * @param handlerId 处理人唯一标识
	 */ 
	void modifyDocumentTaskStatus(String documentId, String handlerId);
	/** 
	 * 删除指定公文对应的公文任务
	 * 
	 * @param documentId 公文的唯一标识
	 * @return 删除的记录数
	 */ 
	int removeDocumentTasks(String documentId);
	int findDocumentTasksCount(QueryConditionVO queryConditionVo,
			String handlerId, boolean status);
}
