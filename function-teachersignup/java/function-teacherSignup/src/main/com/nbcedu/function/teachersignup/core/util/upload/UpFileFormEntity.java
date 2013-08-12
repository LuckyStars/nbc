package com.nbcedu.function.teachersignup.core.util.upload;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

/**
 * 文件上传表单解析实体
 * 
 * @author qinyuan
 * @since 2011-5-11
 */
public class UpFileFormEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 请求参数列表
	 */
	private Map<String, String> paraMap;
	
	/**
	 * 暂时使用fileupload内置对象文件对象，可能为空，可能为null
	 */
	private List<FileItem> fileItems;
	
	public UpFileFormEntity() {
	}

	public UpFileFormEntity(Map<String, String> paraMap,
			List<FileItem> fileItems) {
		this();
		this.paraMap = paraMap;
		this.fileItems = fileItems;
	}


	/**
	 * @return the paraMap
	 */
	public Map<String, String> getParaMap() {
		return paraMap;
	}

	/**
	 * @param paraMap
	 *            the paraMap to set
	 */
	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}

	/**
	 * @return the fileItems
	 */
	public List<FileItem> getFileItems() {
		return fileItems;
	}

	/**
	 * @param fileItems
	 *            the fileItems to set
	 */
	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}
}