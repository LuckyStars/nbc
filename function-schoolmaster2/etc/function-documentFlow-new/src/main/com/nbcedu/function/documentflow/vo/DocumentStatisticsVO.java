/*
 * @Title: DocumentVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Document实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 上午12:01:26
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.vo;

import java.util.List;

/** 
 * <p>Document实体的值对象</p>
 * <p>显示公文5条，处理公文比例</p>
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 上午12:01:26
 */
public class DocumentStatisticsVO {
	private int documentExpired;//过期公文
	private int documentFinished;//已处理公文
	private int documentUnFinished;//待处理公文
	private List<DocumentVO> document;//最新5条公文
	
	public int getDocumentExpired() {
		return documentExpired;
	}
	public void setDocumentExpired(int documentExpired) {
		this.documentExpired = documentExpired;
	}
	public int getDocumentFinished() {
		return documentFinished;
	}
	public void setDocumentFinished(int documentFinished) {
		this.documentFinished = documentFinished;
	}
	public int getDocumentUnFinished() {
		return documentUnFinished;
	}
	public void setDocumentUnFinished(int documentUnFinished) {
		this.documentUnFinished = documentUnFinished;
	}
	public List<DocumentVO> getDocument() {
		return document;
	}
	public void setDocument(List<DocumentVO> document) {
		this.document = document;
	}
}
