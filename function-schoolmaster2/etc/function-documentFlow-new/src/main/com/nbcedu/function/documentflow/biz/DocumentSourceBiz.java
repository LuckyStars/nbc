/*
 * @Title: DocumentSourceBiz.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: DocumentSource实体业务逻辑接口，该接口包含了DocumentSource实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 下午04:52:28
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;
import java.util.List;

import com.nbcedu.function.documentflow.model.DocumentSource;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;

/** 
 * <p>DocumentSource实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 下午04:52:28
 */
public interface DocumentSourceBiz extends DocumentFlowBaseBiz {
	/** 
	 * 新增DocumentSource对象
	 * 
	 * @param documentSourceVo 要新增的DocumentSource对应的值对象
	 * @return DocumentSource对象的唯一标识
	 */ 
	String addDocumentSource(DocumentSourceVO documentSourceVo);
	/** 
	 * 修改DocumentSource对象
	 * 
	 * @param documentSourceVo 要修改的DocumentSource对应的值对象
	 */ 
	void modifyDocumentSource(DocumentSourceVO documentSourceVo);
	/** 
	 * 返回一个符合条件的DocumentSource对象
	 * 
	 * @param documentSourceVo 要返回的DocumentSource对象对应的值对象
	 * @return 要返回的DocumentSource对象
	 */ 
	DocumentSource findDocumentSource(DocumentSourceVO documentSourceVo);
	/** 
	 * 返回指定DocumentSourceVO对象
	 * 
	 * @param id DocumentSourceVO的唯一标识
	 * @return 指定DocumentSourceVO对象
	 */ 
	DocumentSourceVO findDocumentSource(Serializable id);
	/** 
	 * 返回所有DocumentSourceVO对象
	 * 
	 * @return 所有DocumentSourceVO对象的列表
	 */ 
	List<DocumentSourceVO> findDocumentSources();
	/** 
	 * 删除DocumentSource对象
	 * 
	 * @param documentSourceVo 要删除的DocumentSource对应的值对象
	 * @return 删除成功返回true，删除失败返回false
	 */ 
	boolean removeDocumentSource(DocumentSourceVO documentSourceVo);
}
