/*
 * @Title: CommentBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: CommentBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-20 下午08:16:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-20                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nbcedu.function.documentflow.biz.CommentBiz;
import com.nbcedu.function.documentflow.model.Comment;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.CommentVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.integration.uc.client.facade.BaseClient;

/** 
 * <p>CommentBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-20 下午08:16:12
 */
public class CommentBizImpl extends DocumentFlowBaseBizImpl implements CommentBiz {
	
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.CommentBiz#addComment(com.nbcedu.function.documentflow.vo.CommentVO, com.nbcedu.function.documentflow.vo.DocumentVO)
	 */
	@Override
	public void addComment(CommentVO commentVo, DocumentVO documentVo) {
		Document document = (Document) getHibernateDao().get(Document.class, documentVo.getId());
		Comment comment = new Comment();
		
		comment.setContent(commentVo.getContent());
		comment.setCreateTime(new Date());
		comment.setDocument(document);
		comment.setOrderId(findCommentMaxOrder(documentVo.getId()) + 1);
		comment.setReplierId(commentVo.getReplierId());
		
		getHibernateDao().create(comment);
	}
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.CommentBiz#findCommentLatest(com.nbcedu.function.documentflow.model.Document)
	 */
	@Override
	public Comment findCommentLatest(Document document) {
		return (Comment) getHibernateDao().get(
				"FROM Comment c WHERE c.document.id = ? ORDER BY c.orderId DESC", 
				new Object[]{document.getId()});
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.CommentBiz#findComments(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentVO> findComments(String documentId) {
		List<Comment> comments = (List<Comment>) getHibernateDao().retrieve(
				"FROM Comment c WHERE c.document.id = ? ORDER BY c.orderId DESC", 
				new Object[]{documentId});
		List<CommentVO> commentVoList = new ArrayList<CommentVO>();
		BaseClient client = new BaseClient();
		
		for (Comment comment : comments) {
			CommentVO commentVo = new CommentVO();
			commentVo.setId(comment.getId());
			commentVo.setContent(comment.getContent());
			commentVo.setReplyTime(StringUtil.getDateTimeString(comment.getCreateTime()));
			commentVo.setReplierId(comment.getReplierId());
			commentVo.setReplier(client.queryTeacher(1, comment.getReplierId()).getName());
			
			commentVoList.add(commentVo);
		}
		
		return commentVoList;
	}

	/** 
	 * 返回指定公文的回复数量
	 * 
	 * @param documentId 指定公文的唯一标识
	 * @return 指定公文的回复数量
	 */ 
	private int findCommentCountByDocumentId(String documentId) {
		return getHibernateDao().retrieve("FROM Comment c WHERE c.document.id = ?", 
				new Object[]{documentId}).size();
	}
	
	/** 
	 * 返回指定公文回复序号的最大值
	 * 
	 * @param documentId 指定的公文唯一标识
	 * @return 指定公文回复序号的最大值
	 */ 
	private int findCommentMaxOrder(String documentId) {
		return (findCommentCountByDocumentId(documentId) != 0) ? ((Integer) getHibernateDao().get(
				"SELECT MAX(c.orderId) FROM Comment c WHERE c.document.id = ?", new Object[] {documentId}))
				: 0;
	}
}
