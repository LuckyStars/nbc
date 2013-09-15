/*
 * @Title: AttachmentVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Attachment实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-12 下午01:54:37
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-12                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>Attachment实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-12 下午01:54:37
 */
public class AttachmentVO {
	/**
	 * 附件的唯一标识
	 */
	private String id;
	/**
	 * 附件名称
	 */
	private String fileName;
	/**
	 * 附件的存储路径
	 */
	private String realPath;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the realPath
	 */
	public String getRealPath() {
		return realPath;
	}
	/**
	 * @param realPath the realPath to set
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
}
