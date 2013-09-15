/*
 * @Title: DocumentBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Document实体业务逻辑接口，该接口包含了Document实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 下午02:51:51
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.vo.DocumentStatisticsVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;

/** 
 * <p>Document实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 下午02:51:51
 */
public interface DocumentBiz extends DocumentFlowBaseBiz {
	/** 
	 * 撰写公文，即新增Document对象
	 * 
	 * @param documentVo Document对应的值对象
	 * @return Document的唯一标识
	 */ 
	String addDocument(DocumentVO documentVo);
	/** 
	 * 查询指定公文
	 * 
	 * @param id 要查询的指定公文的唯一标识
	 * @return 指定公文的值对象
	 */ 
	
	DocumentVO findDocument(Serializable id);
	/** 
	 * 返回我的公文
	 * 
	 * @param id 公文实体的唯一标识
	 * @return 公文实体对应的值对象
	 */ 
	DocumentVO findMyDocument(Serializable id);
	/** 
	 * 返回按照回复时间排序的公文列表
	 * 
	 * @param authorId 公文作者的唯一标识
	 * @param pagerUtils 分页信息工具
	 * @return 按照回复时间排序的公文列表
	 */ 
	List<DocumentVO> findDocuments(String authorId, PagerUtils pagerUtils);
	/** 
	 * 返回指定用户撰写的公文的列表
	 * 
	 * @param authorId 指定用户的唯一标识
	 * @param queryConditionVo 给定条件的值对象
	 * @param pagerUtils 分页信息工具实体
	 * @return 指定用户撰写的公文的列表
	 */ 
	List<DocumentVO> findDocuments(String authorId, QueryConditionVO queryConditionVo,
			PagerUtils pagerUtils);
	/** 
	 * 返回已经流转结束的指定用户所发的公文
	 * 
	 * @param authorId 指定用户的唯一标识
	 * @param pagerUtils 分页信息工具
	 * @return 符合条件的公文列表
	 */ 
	List<DocumentVO> findMyDocumentsFinished(String authorId, PagerUtils pagerUtils);
	/** 
	 * 返回已经过期的指定用户所发的公文
	 * 
	 * @param authorId 指定用户的唯一标识
	 * @param pagerUtils 分页信息工具
	 * @return 符合条件的公文列表
	 */ 
	List<DocumentVO> findMyDocumentsExpired(String authorId, PagerUtils pagerUtils);
	/** 
	 * 根据指定条件查询公文数量
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @return 符合条件的公文数量
	 */ 
	int findDocumentCount(QueryConditionVO queryConditionVo);
	int findDocumentFinishedTaskCount(QueryConditionVO queryConditionVo, boolean bool);
	/** 
	 * 根据指定条件查询过期的公文数量
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @return 符合条件的过期公文数量
	 */ 
	int findDocumentExpiredCount(QueryConditionVO queryConditionVo);
	/** 
	 * 修改公文状态
	 * 
	 * @param id 要修改的公文的唯一标识
	 * @param status 要修改的公文状态的标识
	 */ 
	void modifyDocumentStatus(String id, int status);
	/** 
	 * 修改Document对象
	 * 
	 * @param documentVo 要修改的Document的业务对象
	 */ 
	void modifyDocument(DocumentVO documentVo);
	/** 
	 * 添加公文的已接收用户
	 * 
	 * @param documentVo 所接收公文的用户
	 * @param user 用户的唯一标识
	 */ 
	void addDocumentReceivedUser(DocumentVO documentVo, String user);
	/** 
	 * 检查公文是否已经流转完成
	 * 
	 * @param documentVo 进行检查的公文对象
	 * @return 如果流转完成返回true
	 */
	boolean hasFinished(DocumentVO documentVo);
	/** 
	 * 删除公文对象
	 * 
	 * @param documentIds 要删除的公文对象的唯一标识，多个用“,”分隔
	 * @return 删除的公文对象的数量
	 */ 
	int removeDocuments(String documentIds);
	/**
	 * 
	 * 提供 页面最新5条公文
	 * 公文处理比例
	 */
	 DocumentStatisticsVO findDocumentStatistics();
	 /**
	  * 公文数
	  * @param authorId
	  * @return 用户处理的公文数
	  */
	 public int findMyDocumentStatusCount(int status);
	 /**
	  * 公文列表
	  * @param status 公文状态
	  * @param num 公文数量
	  * @return 公文列表
	  */
	 public List<Document> findMyDocumentTopFive(int status,int num);
	 
	 /**
	  * 
	  * @param pubPid
	  * @param pager
	  * @param leftDays
	  * @return
	  * @author xuechong
	  */
	 public List<DocumentVO> findFlowingDocuments(String pubPid,PagerUtils pager,Date start,Date end,int shut);
	 
	 /**
	  * 删除公文
	  * @param id
	  * @author xuechong
	  */
	 public boolean removeDocumentById(String id);
	 @Deprecated
	 public int findMyDocumentsFinishedCount(String authorId);
	 /**
	  * 用户的未处理的公文数
	  * @param authorId
	  * @return 用户未处理的公文数
	  */
	 @Deprecated
	 public int findMyDocumentsUnFinishedCount(String authorId);
	 /**
	  * 用户的过期未处理的公文数
	  * @param authorId
	  * @return 用户过期未处理的公文数
	  */
	 @Deprecated
		public int findMyDocumentsExpiredCount(String authorId);
	 /**
	  * 修改自己的关闭状态 0未关闭 ；1已关闭
	  * @param id
	  * @param shut
	  */
	 public void modifyDocumentShut(String id, int shut) ;
}
