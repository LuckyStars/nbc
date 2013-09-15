/*
 * @Title: DocumentTaskAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: DocumentTaskVO的请求处理控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-12 下午08:36:00
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-12                          
 */
package com.nbcedu.function.documentflow.action;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.core.privilege.model.User;
import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.utils.Struts2Utils;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;
import com.nbcedu.function.documentflow.vo.DocumentTaskVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.opensymphony.xwork2.ActionContext;

/** 
 * <p>DocumentTaskVO的请求处理控制器</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-12 下午08:36:00
 */
public class DocumentTaskAction {

	/**
	 * 公文任务列表
	 */
	private List<DocumentTaskVO> documentTaskVoList;
	/**
	 * 发文单位列表
	 */
	private List<DocumentSourceVO> documentSourceVoList;
	/**
	 * QueryConditionVO对象
	 */
	private QueryConditionVO queryConditionVo = new QueryConditionVO();
	/**
	 * PagerUtils对象
	 */
	private PagerUtils pagerUtils = new PagerUtils();
	/**
	 * DocumentTaskBiz对象
	 */
	private DocumentTaskBiz documentTaskBiz;
	/**
	 * DocumentSourceBiz对象
	 */
	private DocumentSourceBiz documentSourceBiz;
	
	/** 
	 * 显示“待处理公文”列表
	 * 
	 * @return “待处理公文”列表页对应的result名称
	 */ 
	public String listUnhandled() {
		String handlerId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		pagerUtils.setPageSize(10);
		addOffset(pagerUtils);
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		documentTaskVoList = documentTaskBiz.findDocumentTasks(queryConditionVo, pagerUtils, handlerId, false);
		return "unhandledDocList";
	}
	public void sumUnHandled(){
		String uid =  ServletActionContext.getRequest().getParameter("userId");
		int s = documentTaskBiz.findDocumentTasksCount(queryConditionVo, uid, false);
		Struts2Utils.renderText(String.valueOf(s));
	}
	private void addOffset(PagerUtils p){
		String offset = ServletActionContext.getRequest().getParameter("pager.offset");
		if(!StringUtils.isEmpty(offset)){
			p.setPageIndex(Integer.parseInt(offset)/p.getPageSize());
		}
	}
	/**
	 * 所有公文
	 * @return
	 */
	public String listAll() {
		pagerUtils.setPageSize(10);
		addOffset(pagerUtils);
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		documentTaskVoList = documentTaskBiz.findDocumentTasks(queryConditionVo, pagerUtils, null, false);
		return "allDocList";
	}
	/** 
	 * 显示“已处理公文”列表
	 * 
	 * @return “已处理公文”列表页对应的result名称
	 */ 
	public String listHandled() {
		String handlerId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		pagerUtils.setPageSize(10);
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		documentTaskVoList = documentTaskBiz.findDocumentTasks(queryConditionVo, pagerUtils, handlerId, true);
		return "handledDocList";
	}
	
	/**
	 * @return the documentTaskVoList
	 */
	public List<DocumentTaskVO> getDocumentTaskVoList() {
		return documentTaskVoList;
	}

	/**
	 * @param documentTaskVoList the documentTaskVoList to set
	 */
	public void setDocumentTaskVoList(List<DocumentTaskVO> documentTaskVoList) {
		this.documentTaskVoList = documentTaskVoList;
	}

	/**
	 * @return the documentSourceVoList
	 */
	public List<DocumentSourceVO> getDocumentSourceVoList() {
		return documentSourceVoList;
	}

	/**
	 * @param documentSourceVoList the documentSourceVoList to set
	 */
	public void setDocumentSourceVoList(List<DocumentSourceVO> documentSourceVoList) {
		this.documentSourceVoList = documentSourceVoList;
	}

	/**
	 * @return the queryConditionVo
	 */
	public QueryConditionVO getQueryConditionVo() {
		return queryConditionVo;
	}

	/**
	 * @param queryConditionVo the queryConditionVo to set
	 */
	public void setQueryConditionVo(QueryConditionVO queryConditionVo) {
		this.queryConditionVo = queryConditionVo;
	}

	/**
	 * @return the pagerUtils
	 */
	public PagerUtils getPagerUtils() {
		return pagerUtils;
	}

	/**
	 * @param pagerUtils the pagerUtils to set
	 */
	public void setPagerUtils(PagerUtils pagerUtils) {
		this.pagerUtils = pagerUtils;
	}

	/**
	 * @param documentTaskBiz the documentTaskBiz to set
	 */
	public void setDocumentTaskBiz(DocumentTaskBiz documentTaskBiz) {
		this.documentTaskBiz = documentTaskBiz;
	}

	/**
	 * @param documentSourceBiz the documentSourceBiz to set
	 */
	public void setDocumentSourceBiz(DocumentSourceBiz documentSourceBiz) {
		this.documentSourceBiz = documentSourceBiz;
	}
}
