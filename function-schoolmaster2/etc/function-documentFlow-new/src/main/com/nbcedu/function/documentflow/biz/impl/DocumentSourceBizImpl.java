/*
 * @Title: DocumentSourceBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: DocumentSourceBiz实现类
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 下午05:22:45
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.model.DocumentSource;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;

/** 
 * <p>DocumentSourceBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 下午05:22:45
 */
public class DocumentSourceBizImpl extends DocumentFlowBaseBizImpl implements DocumentSourceBiz {
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#addDocumentSource(com.nbcedu.function.documentflow.vo.DocumentSourceVO)
	 */
	@Override
	public String addDocumentSource(DocumentSourceVO documentSourceVo) {
		DocumentSource documentSource = new DocumentSource();
		documentSource.setDisplayName(documentSourceVo.getDisplayName());
		documentSource.setStatus("1");
		getHibernateDao().create(documentSource);
		
		return documentSource.getId();
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#findDocumentSource(com.nbcedu.function.documentflow.vo.DocumentSourceVO)
	 */
	@Override
	public DocumentSource findDocumentSource(DocumentSourceVO documentSourceVo) {
		return (DocumentSource) getHibernateDao().get(DocumentSource.class, documentSourceVo.getId());
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#findDocumentSource(java.io.Serializable)
	 */
	@Override
	public DocumentSourceVO findDocumentSource(Serializable id) {
		DocumentSource documentSource = (DocumentSource) getHibernateDao().get(DocumentSource.class, id);
		DocumentSourceVO documentSourceVo = new DocumentSourceVO();
		try {
			BeanUtils.copyProperties(documentSourceVo, documentSource);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return documentSourceVo;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#findDocumentSources()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentSourceVO> findDocumentSources() {
		List<DocumentSourceVO> documentSourceVoList = new ArrayList<DocumentSourceVO>(); 
		List<DocumentSource> documentSourceList = (List<DocumentSource>) getHibernateDao().retrieve(
				"FROM DocumentSource ds WHERE ds.status = ?", new Object[] {"1"});
		
		for (DocumentSource documentSource : documentSourceList) {
			DocumentSourceVO documentSourceVo = new DocumentSourceVO();
			documentSourceVo.setId(documentSource.getId());
			documentSourceVo.setDisplayName(documentSource.getDisplayName());
			documentSourceVoList.add(documentSourceVo);
		}
		
		return documentSourceVoList;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#modifyDocumentSource(com.nbcedu.function.documentflow.vo.DocumentSourceVO)
	 */
	@Override
	public void modifyDocumentSource(DocumentSourceVO documentSourceVo) {
		DocumentSource documentSource = findDocumentSource(documentSourceVo);
		documentSource.setDisplayName(documentSourceVo.getDisplayName());
		
		getHibernateDao().update(documentSource);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentSourceBiz#removeDocumentSource(com.nbcedu.function.documentflow.vo.DocumentSourceVO)
	 */
	@Override
	public boolean removeDocumentSource(DocumentSourceVO documentSourceVo) {
		if (isInuse(documentSourceVo)) {
			return false;
		}
		DocumentSource documentSource = findDocumentSource(documentSourceVo);
		documentSource.setStatus("0");
		
		getHibernateDao().update(documentSource);
		
		return true;
	}
	
	/** 
	 * 判断指定发文单位是否在使用中，true:在使用中，false：不在使用中
	 * 
	 * @param documentSourceVo 要进行判断的发文单位对象
	 * @return true:在使用中，false：不在使用中
	 */ 
	private boolean isInuse(DocumentSourceVO documentSourceVo) {
		int count = ((Long) getHibernateDao().get(
				"SELECT COUNT(*) FROM Document d WHERE d.documentSourceId = ? AND d.status = ?", 
				new Object[]{documentSourceVo.getId(), 1})).intValue();
		if (count > 0) {
			return true;
		}
		return false;
	}
}
