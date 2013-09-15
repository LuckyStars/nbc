/*
 * @Title: CommentBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Comment实体业务逻辑接口，该接口包含了Comment实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-20 下午07:37:20
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-20                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.List;

import com.nbcedu.function.documentflow.model.Comment;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.vo.CommentVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;

/** 
 * <p>Comment实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-20 下午07:37:20
 */
public interface CommentBiz extends DocumentFlowBaseBiz {
	/** 
	 * 新增回复
	 * 
	 * @param commentVo 要新增回复的值对象
	 * @param documentVo 要新增回复对应公文的值对象
	 */ 
	void addComment(CommentVO commentVo, DocumentVO documentVo);
	/** 
	 * 返回最新的回复
	 * 
	 * @param document 回复所属的公文
	 * @return 最新的回复
	 */ 
	Comment findCommentLatest(Document document);
	/** 
	 * 返回指定公文对应的回复
	 * 
	 * @param documentId 指定公文的唯一标识
	 * @return 回复列表
	 */ 
	List<CommentVO> findComments(String documentId);
}
