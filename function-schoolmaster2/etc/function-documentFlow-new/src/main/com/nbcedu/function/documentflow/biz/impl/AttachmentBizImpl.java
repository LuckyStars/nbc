/*
 * @Title: AttachmentBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: AttachmentBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-12 下午02:16:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-12                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;

import com.nbcedu.function.documentflow.biz.AttachmentBiz;
import com.nbcedu.function.documentflow.model.Attachment;
import com.nbcedu.function.documentflow.vo.AttachmentVO;

/** 
 * <p>AttachmentBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-12 下午02:16:12
 */
public class AttachmentBizImpl extends DocumentFlowBaseBizImpl implements AttachmentBiz {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.AttachmentBiz#addAttachment(com.nbcedu.function.documentflow.vo.AttachmentVO)
	 */
	@Override
	public String addAttachment(AttachmentVO attachmentVo) {
		Attachment attachment = new Attachment();
		attachment.setFileName(attachmentVo.getFileName());
		attachment.setRealPath(attachmentVo.getRealPath());
		
		getHibernateDao().create(attachment);
		return attachment.getId();
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.AttachmentBiz#findAttachment(java.io.Serializable)
	 */
	@Override
	public Attachment findAttachment(Serializable id) {
		return (Attachment) getHibernateDao().get(Attachment.class, id);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.AttachmentBiz#removeAttachment(java.io.Serializable)
	 */
	@Override
	public void removeAttachment(Serializable id) {
		getHibernateDao().delete(Attachment.class, id);
	}
}
