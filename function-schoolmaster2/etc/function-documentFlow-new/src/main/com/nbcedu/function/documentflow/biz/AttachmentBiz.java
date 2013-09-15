/*
 * @Title: AttachmentBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Attachment实体业务逻辑接口，该接口包含了Attachment实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-11 下午04:06:25
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-11                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;

import com.nbcedu.function.documentflow.model.Attachment;
import com.nbcedu.function.documentflow.vo.AttachmentVO;

/** 
 * <p>Attachment实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-11 下午04:06:25
 */
public interface AttachmentBiz extends DocumentFlowBaseBiz {
	/** 
	 * 创建附件对象
	 * 
	 * @param attachmentVo 要创建的附件对象对应的值对象
	 * @return 新增附件的唯一标识
	 */ 
	String addAttachment(AttachmentVO attachmentVo);
	/** 
	 * 根据指定条件查询附件
	 * 
	 * @param id 附件对象的唯一标识
	 * @return 附件的业务对象
	 */ 
	Attachment findAttachment(Serializable id);
	/** 
	 * 删除指定附件
	 * 
	 * @param id 要删除的指定附件的唯一标识
	 */ 
	void removeAttachment(Serializable id);
}
