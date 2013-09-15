/*
 * @Title: DocumentAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: DocumentVO的请求处理控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-8 下午04:02:45
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-8                          
 */
package com.nbcedu.function.documentflow.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;
import com.nbcedu.function.documentflow.biz.AttachmentBiz;
import com.nbcedu.function.documentflow.biz.CommentBiz;
import com.nbcedu.function.documentflow.biz.DocumentBiz;
import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.biz.ForwardingBiz;
import com.nbcedu.function.documentflow.biz.NotifyProfileBiz;
import com.nbcedu.function.documentflow.biz.RouteBiz;
import com.nbcedu.function.documentflow.model.Attachment;
import com.nbcedu.function.documentflow.model.DocumentTask;
import com.nbcedu.function.documentflow.utils.PortalMessageUtil;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.CommentVO;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.function.documentflow.vo.ForwardingVO;
import com.nbcedu.function.documentflow.vo.NotifyProfileVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.nbcedu.function.documentflow.vo.RouteVO;
import com.opensymphony.xwork2.ActionContext;

/** 
 * <p>DocumentVO的请求处理控制器</p>
 * @author Wang Zhuoxuan
 * Create at:2011-9-8 下午04:02:45
 */
public class DocumentAction extends DefaultAbstractAction{

	private DocumentVO documentVo = new DocumentVO();
	private NotifyProfileVO notifyProfileVo;
	private CommentVO commentVo = new CommentVO();
	private QueryConditionVO queryConditionVo = new QueryConditionVO();
	private PagerUtils pagerUtils = new PagerUtils();
	private List<NotifyProfileVO> notifyProfileVoList;
	private List<DocumentSourceVO> documentSourceVoList;
	private List<DocumentVO> documentVoList;
	private List<RouteVO> routeVoList;
	private Date flowStart;
	private Date flowEnd;
	private Integer flowDays;
	private Integer shut;
	
	
	private DocumentBiz documentBiz;
	private NotifyProfileBiz notifyProfileBiz;
	private DocumentSourceBiz documentSourceBiz;
	private DocumentTaskBiz documentTaskBiz;
	private CommentBiz commentBiz;
	private AttachmentBiz attachmentBiz;
	private RouteBiz routeBiz;
	private ForwardingBiz forwardingBiz;
	
	private static final Logger logger = Logger.getLogger(DocumentAction.class);
	
	/** 
	 * 预加载一些信息并跳转到撰写公文页面
	 * @return 撰写公文页面对应的result名称
	 */ 
	public String create() {
		String creatorId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		notifyProfileVoList = notifyProfileBiz.findNotifyProfiles();
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		notifyProfileVo = notifyProfileBiz.findNotifyProfileDefault(); //默认的短信提醒方案
		routeVoList = routeBiz.findRoutes(creatorId);
		//为EASY-UI时间控件赋初始值
		documentVo.setExpireTime(StringUtil.getDateTimeString(new Date()));
		ActionContext.getContext().put("isEdit", notifyProfileVo.getIsEdit());
		return "newDoc";
	}
	
	/** 
	 * 发布公文，跳转至“我的公文”
	 * @return “我的公文”的result名称
	 */ 
	public String pub() {
		String authorId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		String[] attachmentIds = ServletActionContext.getRequest().getParameterValues("attachmentIds");
		String[] receiverIds = ServletActionContext.getRequest().getParameterValues("documentVo.receiverIds");
		String[] notifierIds = ServletActionContext.getRequest().getParameterValues("documentVo.notifierIds");
		String docId = null;
		//如果提醒模式不可编辑则所要提醒的人与接收人相同
		if (!notifyProfileBiz.findNotifyProfile(documentVo.getNotifyProfileId()).getIsEdit()) {
			notifierIds = receiverIds;
		}
		
		if(documentVo.getTitle()!=null){
			documentVo.setTitle(documentVo.getTitle().trim());
		}
		documentVo.setAuthorId(authorId);
		documentVo.setStatus("1");
		documentVo.setReceiverIds(receiverIds);
		if (attachmentIds != null && attachmentIds.length > 0) {
			Set<Attachment> attachments = new HashSet<Attachment>();
			for (String aid : attachmentIds) {
				attachments.add(attachmentBiz.findAttachment(aid));
			}
			documentVo.setAttachments(attachments);
		}
		if (notifierIds != null) {
			documentVo.setNotifierIds(notifierIds);
		}
		
		if (!StringUtil.isBlank(documentVo.getId())) {
			docId = documentVo.getId();
			documentBiz.modifyDocument(documentVo);
		} else {
			docId = documentBiz.addDocument(documentVo);
		}
		documentTaskBiz.addDocumentTask(documentBiz.findDocument(docId), "0", getBasePath());
		
		return "listMy";
	}

	/** 
	 * 检查当前的紧急程度模式是否可选人
	 * v2
	 */ 
	public void checkEditable() {
		String nid = ServletActionContext.getRequest().getParameter("nid");
		NotifyProfileVO profile = notifyProfileBiz.findNotifyProfile(nid);
		String flag = profile.getIsEdit().toString();
		String content = profile.getContent();
		PrintWriter out = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			out = ServletActionContext.getResponse().getWriter();
			out.print(flag + ";" + content);
			out.flush();
		} catch (IOException e) {
			logger.error("检查当前的紧急程度模式是否可选人时出现错误", e);
			e.printStackTrace();
		}finally{
			if(out!=null){out.close();}
		}
	}
	
	/** 
	 * 保存公文
	 * 
	 */
	public void save() {
		String userId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		String docId = ServletActionContext.getRequest().getParameter("docId");
		String title = ServletActionContext.getRequest().getParameter("title");
		String aids = ServletActionContext.getRequest().getParameter("aids");
		String notifyProfileId = ServletActionContext.getRequest().getParameter("notifyProfileId");
		String documentSourceId = ServletActionContext.getRequest().getParameter("documentSourceId");
		String period = ServletActionContext.getRequest().getParameter("period");
		String receiverIds = ServletActionContext.getRequest().getParameter("receiverIds");
		String notifierIds = ServletActionContext.getRequest().getParameter("notifierIds");
		String expireTime = ServletActionContext.getRequest().getParameter("expireTime");
		String notifyContent = ServletActionContext.getRequest().getParameter("notifyContent");
		String content = ServletActionContext.getRequest().getParameter("content");
		String responseText;
		
		if (!StringUtil.isBlank(docId)) {
			documentVo = documentBiz.findDocument(docId);
			documentVo.setStatus("");
		} else {
			documentVo = new DocumentVO();
			documentVo.setStatus("0");
		}
		
		documentVo.setAuthorId(userId);
		documentVo.setTitle(title.trim());
		documentVo.setNotifyProfileId(notifyProfileId);
		documentVo.setDocumentSourceId(documentSourceId);
		documentVo.setPeriod(period);
		documentVo.setContent(content);
		documentVo.setNotifyContent(notifyContent);
		if ("0".equals(period)) {
			documentVo.setExpireTime(expireTime);
		}
		if (!StringUtil.isBlank(aids)) {
			Set<Attachment> attachments = new HashSet<Attachment>();
			for (String aid : aids.split(",")) {
				attachments.add(attachmentBiz.findAttachment(aid));
			}
			documentVo.setAttachments(attachments);
		}
		if (!StringUtil.isBlank(receiverIds)) {
			documentVo.setReceiverIds(receiverIds.split(","));
		}
		if (!StringUtil.isBlank(notifierIds)) {
			documentVo.setNotifierIds(notifierIds.split(","));
		}
		
		if (!StringUtil.isBlank(docId)) {
			documentBiz.modifyDocument(documentVo);
			documentTaskBiz.removeDocumentTasks(docId);
			responseText = docId;
		} else {
			responseText = documentBiz.addDocument(documentVo);
		}
		PrintWriter out =null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.print(responseText);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){out.close();}
		}
	}
	
	/** 
	 * 显示“我的公文”列表，分页每页10条数据
	 * @return 跳转到“我的公文”页
	 */ 
	public String listMy() {
		String authorId = this.getCurUserPId();
		pagerUtils.setPageSize(10);
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		documentVoList = documentBiz.findDocuments(authorId, queryConditionVo, pagerUtils);
		return "myDocList";
	}
	public String shutMy(){
		String docId = ServletActionContext.getRequest().getParameter("docId");
		this.documentBiz.modifyDocumentShut(docId, 1);
		return "toListFlowing";
	}
//	modifyDocumentShut
	/**
	 * 按过期时间查询我的公文
	 * @return
	 * @author xuechong
	 */
	public String listFlowing(){
		if(this.pagerUtils.getPageIndex()<=0){
			this.pagerUtils.setPageIndex(1);
		}
		pagerUtils.setPageSize(DEFAULT_PAGE_SIZE);
		if(this.flowDays!=null&&this.flowDays>0){
			this.flowStart = new Date();
			this.flowEnd = new Date(
					System.currentTimeMillis()+
					1000l*60l*60l*24l*(this.flowDays.longValue()));
		}
		if(this.shut==null){
			this.shut=0;
		}
		this.documentVoList = this.documentBiz.
			findFlowingDocuments(getCurUserPId(), this.pagerUtils, 
								this.flowStart,this.flowEnd,this.shut);
		if(this.flowDays!=null && this.flowDays>0){
			this.flowStart = null;	
			this.flowEnd =null;
		}else if(this.flowDays==null){
			this.flowDays = 90;
		}
		return "listFlowing";
	}
	public Integer getFlowDays() {
		return flowDays;
	}

	public void setFlowDays(Integer flowDays) {
		this.flowDays = flowDays;
	}

	public Integer getShut() {
		return shut;
	}

	public void setShut(Integer shut) {
		ServletActionContext.getRequest().setAttribute("shut" ,shut);
		this.shut = shut;
	}

	/** 
	 * 查看我的公文
	 * @return 我的公文详细页
	 */ 
	public String viewMy() {
		String docId = ServletActionContext.getRequest().getParameter("docId");
		String userId = this.getCurUserPId();
		documentVo = documentBiz.findMyDocument(docId);
		putFowardPids();
		//如果公文可编辑跳转到编辑页，否则跳转到查看页
		if (documentVo.getIsEditable()) {
			notifyProfileVoList = notifyProfileBiz.findNotifyProfiles();
			documentSourceVoList = documentSourceBiz.findDocumentSources();
			notifyProfileVoList.remove(notifyProfileBiz.findNotifyProfile(documentVo.getNotifyProfileId()));
			if (!"-1".equals(documentVo.getDocumentSourceId())) {
				documentSourceVoList.remove(documentSourceBiz.findDocumentSource(documentVo.getDocumentSourceId()));
			}
			
			notifyProfileVo = notifyProfileBiz.findNotifyProfile(documentVo.getNotifyProfileId());
			routeVoList = routeBiz.findRoutes(userId);
			String[] receiverIds = documentVo.getReceiverIds();
			String[] notifierIds = documentVo.getNotifierIds();
			if (receiverIds != null && receiverIds.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String receiverId : receiverIds) {
					sb.append(receiverId).append(",");
				}
				sb.substring(0, sb.lastIndexOf(","));
				ActionContext.getContext().put("receiverIds", receiverIds);
			}
			if (notifierIds != null && notifierIds.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String notifierId : notifierIds) {
					sb.append(notifierId).append(",");
				}
				sb.substring(0, sb.lastIndexOf(","));
				ActionContext.getContext().put("notifierIds", notifierIds);
			}
			if (documentVo.getAttachments() != null) {
				Set<Attachment> attachments = new HashSet<Attachment>();
				Iterator<Attachment> it = documentVo.getAttachments().iterator();
				while (it.hasNext()) {
					attachments.add(it.next());
				}
				documentVo.setAttachments(attachments);
			}
			ActionContext.getContext().put("isEdit", notifyProfileVo.getIsEdit());
			return "editMyDoc";
		}
		return "viewMyDoc";
	}
	/** 
	 * 公文追踪-查看我的公文
	 * @return 我的公文详细页
	 */ 
	public String viewMyFlowing() {
		String docId = ServletActionContext.getRequest().getParameter("docId");
		String userId = this.getCurUserPId();
		documentVo = documentBiz.findMyDocument(docId);
		putFowardPids();
		//如果公文可编辑跳转到编辑页，否则跳转到查看页
		if (documentVo.getIsEditable()) {
			notifyProfileVoList = notifyProfileBiz.findNotifyProfiles();
			documentSourceVoList = documentSourceBiz.findDocumentSources();
			notifyProfileVoList.remove(notifyProfileBiz.findNotifyProfile(documentVo.getNotifyProfileId()));
			if (!"-1".equals(documentVo.getDocumentSourceId())) {
				documentSourceVoList.remove(documentSourceBiz.findDocumentSource(documentVo.getDocumentSourceId()));
			}
			
			notifyProfileVo = notifyProfileBiz.findNotifyProfile(documentVo.getNotifyProfileId());
			routeVoList = routeBiz.findRoutes(userId);
			String[] receiverIds = documentVo.getReceiverIds();
			String[] notifierIds = documentVo.getNotifierIds();
			if (receiverIds != null && receiverIds.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String receiverId : receiverIds) {
					sb.append(receiverId).append(",");
				}
				sb.substring(0, sb.lastIndexOf(","));
				ActionContext.getContext().put("receiverIds", receiverIds);
			}
			if (notifierIds != null && notifierIds.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String notifierId : notifierIds) {
					sb.append(notifierId).append(",");
				}
				sb.substring(0, sb.lastIndexOf(","));
				ActionContext.getContext().put("notifierIds", notifierIds);
			}
			if (documentVo.getAttachments() != null) {
				Set<Attachment> attachments = new HashSet<Attachment>();
				Iterator<Attachment> it = documentVo.getAttachments().iterator();
				while (it.hasNext()) {
					attachments.add(it.next());
				}
				documentVo.setAttachments(attachments);
			}
			ActionContext.getContext().put("isEdit", notifyProfileVo.getIsEdit());
			return "editMyFlowingDoc";
		}
		return "viewMyFlowingDoc";
	}
	
	/** 
	 * 查看未处理公文
	 * 
	 * @return 未处理公文的详细页面对应的result的名称
	 */ 
	public String viewUnhandled() {
		String docId = ServletActionContext.getRequest().getParameter("docId");
		User currentUser = this.getCurUser();
		documentVo = documentBiz.findDocument(docId);
		
		DocumentTask task = documentTaskBiz.findDocumentTaskByHanderId(docId, currentUser.getPid());
		if(task!=null && task.getIsHandled()){
			ActionContext.getContext().put("docId", docId);
			return "viewHandled";
		}
		if (!"流转中".equals(documentVo.getStatus())) { //已过期
			ActionContext.getContext().put("docId", docId);
			ActionContext.getContext().put("view", "unhandled");
			return "viewHandled";
		}else{
			// 添加公文的已接收用户
			documentBiz.addDocumentReceivedUser(documentVo, currentUser.getPid());
			docHandled();
		}
		//获取公文任务类型以判断是否有转发功能
		String taskType = ServletActionContext.getRequest().getParameter("taskType");
		putFowardPids();
		//从消息链接进来后重新载入角色列表
		if (ActionContext.getContext().getSession().get("roleList") == null) {
			List<String> roleList = new ArrayList<String>();
			for (Role role : currentUser.getRoles()) {
				roleList.add(role.getName());
			}
			ActionContext.getContext().getSession().put("roleList", roleList);
		}
		
		ActionContext.getContext().put("taskType", taskType);
		return "handlingDoc";
	}
	/**
	 * 放入流转人ID
	 * @author xuechong
	 */
	private void putFowardPids() {
		User currentUser = this.getCurUser();
		StringBuilder pids = new StringBuilder();
		if (documentVo.getReceiverIds() != null && documentVo.getReceiverIds().length > 0) {
			for (String pid : documentVo.getReceiverIds()) {
				pids.append(pid);
				pids.append(",");
			}
		}
		if (documentVo.getForwardingIds()!= null && documentVo.getForwardingIds().length > 0) {
			for (String pid : documentVo.getForwardingIds()) {
				pids.append(pid);
				pids.append(",");
			}
		}
		pids.append(currentUser.getPid());
		pids.append(",");
		pids.append(documentVo.getAuthorId());
		ActionContext.getContext().put("pids", pids.toString());
	}
	
	/** 
	 * 查看已处理公文
	 * @return 已处理公文详细页对应的result名称
	 */ 
	public String viewHandled() {
		String docId = ServletActionContext.getRequest().getParameter("docId");
		String view = (String) ActionContext.getContext().get("view"); 
		if (StringUtil.isBlank(docId)) {
			docId = (String) ActionContext.getContext().get("docId");
		}
		if (!StringUtil.isBlank(view)) {
			ActionContext.getContext().put("view", view);
		}
		
		documentVo = documentBiz.findDocument(docId);
		return "handledDoc";
	}
	
	/** 
	 * 处理公文
	 * @return 跳转至“我的公文”列表页对应的视图
	 */ 
	public String handling() {
		String userId = this.getCurUserPId();
		String[] forwardings = ServletActionContext.getRequest().getParameterValues("forwardings");
		String taskType = ServletActionContext.getRequest().getParameter("taskType");
		documentVo.setAuthorId(userId);
		//如果回复不为空则保存回复
		if (StringUtils.isNotBlank(commentVo.getContent())) {
			commentVo.setReplierId(userId);
			commentBiz.addComment(commentVo, documentVo);
		}
		
		//保存转发信息，将转发信息与Document实体关联
		fowardingDoc(userId, forwardings);
		// 添加公文的已接收用户
		if (taskType.equals("0")) {
			documentBiz.addDocumentReceivedUser(documentVo, userId);
		}
		//docHandled();
		
		return "listUnhandled";
	}
	
	/**
	 * 查看公文时将当前公文设置为已处理
	 * 
	 * @param userId
	 * @author xuechong
	 * @since 2013 5 9
	 */
	public void alreadyRead() {
		String result ;
		try {
			docHandled();
			result = "suc";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.write(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
		
	}
	
	/**
	 * 公文状态改为已处理
	 * 
	 * @author xuechong
	 */
	private void docHandled() {
		String userId = this.getCurUserPId();
		//处理公文任务，修改当前用户公文任务的处理状态
		documentTaskBiz.modifyDocumentTaskStatus(documentVo.getId(), userId); 
		// 添加公文的已接收用户
		//documentBiz.addDocumentReceivedUser(documentVo, userId);
		//如果公文流转已完成则将公文状态修改为“已结束”
		if (documentBiz.hasFinished(documentVo)) {
			documentBiz.modifyDocumentStatus(documentVo.getId(), 2);
			PortalMessageUtil.delPortalPushByDocId(documentVo.getId());
		}
	}

	
	/**
	 * 增加流转人
	 * @return
	 * @author xuechong
	 * @since 2013-5-9
	 */
	public String addPerson(){
		String[] forwardings = ServletActionContext.getRequest().getParameterValues("forwardings");
		String userId = this.getCurUserPId();
		fowardingDoc(userId, forwardings);
		return this.listMy();
	}
	
	/** 
	 * 保存流转路径
	 * v2
	 */ 
	public void saveRoute() {
		String routeName = ServletActionContext.getRequest().getParameter("routeName");
		String receiverIds = ServletActionContext.getRequest().getParameter("receiverIds");
		String creatorId = this.getCurUserPId();
		RouteVO routeVo = new RouteVO();
		routeVo.setRouteName(routeName);
		routeVo.setFlow(receiverIds);
		routeVo.setCreatorId(creatorId);
		routeBiz.addRoute(routeVo);
		try {
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 删除流转路径
	 * 
	 */ 
	public void removeRoute() {
		String routeId = ServletActionContext.getRequest().getParameter("routeId");
		routeBiz.removeRoute(routeId);
		try {
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 判断公文是否被处理过<br/>
	 * 如果已经有人处理,则返回t<br/>
	 * 如果没人处理返回f<br/>
	 * @author xuechong
	 */
	public void hasViewed(){
		String result = "f";
		this.documentVo = this.documentBiz.findDocument(this.documentVo.getId());
		if(documentVo.getHandledCount()>0){
			result = "t";
		}
		PrintWriter out =null;
		try{
			out = ServletActionContext.getResponse().getWriter();
			out.write(result);
			out.flush();
		} catch (IOException e) {
			logger.error("查询公文已处理人数,返回结果时出错", e);
		}finally{if(out!=null){out.close();}}
	}
	/**
	 * 删除公文
	 * @return
	 * @author xuechong
	 */
	public String del(){
		boolean result = this.documentBiz.removeDocumentById(this.documentVo.getId());
		return this.listMy();
	}
	//////////////////////////////
	/////////private////////////
	//////////////////////////////
	private String getBasePath() {
		String path = ServletActionContext.getRequest().getContextPath();
		String basePath = ServletActionContext.getRequest().getScheme() + "://" 
			+ ServletActionContext.getRequest().getServerName() + ":" 
			+ ServletActionContext.getRequest().getServerPort() + path;
		return basePath;
	}
	
	/**
	 * 流转
	 * @return
	 * @author xuechong
	 * @since 2013-5-9
	 */
	private void fowardingDoc(String userId, String[] forwardings) {
		if (forwardings != null) {
			documentVo.setForwardingIds(forwardings);
			ForwardingVO forwardingVo = new ForwardingVO();
			forwardingVo.setDocumentId(documentVo.getId());
			forwardingVo.setForwardingUser(userId);
			forwardingVo.setReceivers(forwardings);
			forwardingBiz.addForwarding(forwardingVo);
			documentTaskBiz.addDocumentTask(documentVo, "1", getBasePath());
		}
	}
	
	///////////////////////////
	/////GETTERS&SETTERS//////
	/////////////////////////
	
	public DocumentVO getDocumentVo() {
		return documentVo;
	}
	public Date getFlowStart() {
		return flowStart;
	}
	public void setFlowStart(Date flowStart) {
		this.flowStart = flowStart;
	}
	public Date getFlowEnd() {
		return flowEnd;
	}
	public void setFlowEnd(Date flowEnd) {
		this.flowEnd = flowEnd;
	}
	public void setDocumentVo(DocumentVO documentVo) {
		this.documentVo = documentVo;
	}
	public NotifyProfileVO getNotifyProfileVo() {
		return notifyProfileVo;
	}
	public void setNotifyProfileVo(NotifyProfileVO notifyProfileVo) {
		this.notifyProfileVo = notifyProfileVo;
	}
	public CommentVO getCommentVo() {
		return commentVo;
	}
	public void setCommentVo(CommentVO commentVo) {
		this.commentVo = commentVo;
	}
	public QueryConditionVO getQueryConditionVo() {
		return queryConditionVo;
	}
	public void setQueryConditionVo(QueryConditionVO queryConditionVo) {
		this.queryConditionVo = queryConditionVo;
	}
	public PagerUtils getPagerUtils() {
		return pagerUtils;
	}
	public void setPagerUtils(PagerUtils pagerUtils) {
		this.pagerUtils = pagerUtils;
	}
	public List<NotifyProfileVO> getNotifyProfileVoList() {
		return notifyProfileVoList;
	}
	public void setNotifyProfileVoList(List<NotifyProfileVO> notifyProfileVoList) {
		this.notifyProfileVoList = notifyProfileVoList;
	}
	public List<DocumentSourceVO> getDocumentSourceVoList() {
		return documentSourceVoList;
	}
	public void setDocumentSourceVoList(List<DocumentSourceVO> documentSourceVoList) {
		this.documentSourceVoList = documentSourceVoList;
	}
	public List<DocumentVO> getDocumentVoList() {
		return documentVoList;
	}
	public void setDocumentVoList(List<DocumentVO> documentVoList) {
		this.documentVoList = documentVoList;
	}
	public List<RouteVO> getRouteVoList() {
		return routeVoList;
	}
	public void setRouteVoList(List<RouteVO> routeVoList) {
		this.routeVoList = routeVoList;
	}
	public void setDocumentBiz(DocumentBiz documentBiz) {
		this.documentBiz = documentBiz;
	}
	public void setNotifyProfileBiz(NotifyProfileBiz notifyProfileBiz) {
		this.notifyProfileBiz = notifyProfileBiz;
	}
	public void setDocumentSourceBiz(DocumentSourceBiz documentSourceBiz) {
		this.documentSourceBiz = documentSourceBiz;
	}
	public void setDocumentTaskBiz(DocumentTaskBiz documentTaskBiz) {
		this.documentTaskBiz = documentTaskBiz;
	}
	public void setCommentBiz(CommentBiz commentBiz) {
		this.commentBiz = commentBiz;
	}
	public void setAttachmentBiz(AttachmentBiz attachmentBiz) {
		this.attachmentBiz = attachmentBiz;
	}
	public void setRouteBiz(RouteBiz routeBiz) {
		this.routeBiz = routeBiz;
	}
	public void setForwardingBiz(ForwardingBiz forwardingBiz) {
		this.forwardingBiz = forwardingBiz;
	}
}
