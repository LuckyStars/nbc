/*
 * @Title: Attachment.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 公文附件实体对象，该对象包含了附件的所有属性以及属性的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-2 下午04:11:39
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-2                          
 */
package com.nbcedu.function.documentflow.model;

/** 
 * <p>公文附件实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-2 下午04:11:39
 */
public class Attachment {

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
