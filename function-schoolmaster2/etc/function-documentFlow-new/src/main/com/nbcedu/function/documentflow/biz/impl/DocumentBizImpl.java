/*
 * @Title: DocumentBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: DocumentBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-11 下午02:33:09
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-11                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import hirondelle.date4j.DateTime;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.common.utils.PropertyUtils;
import com.nbcedu.function.documentflow.biz.CommentBiz;
import com.nbcedu.function.documentflow.biz.DocumentBiz;
import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.biz.NotifyProfileBiz;
import com.nbcedu.function.documentflow.biz.SchedulerBiz;
import com.nbcedu.function.documentflow.model.Comment;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.model.DocumentSource;
import com.nbcedu.function.documentflow.model.DocumentTask;
import com.nbcedu.function.documentflow.model.Forwarding;
import com.nbcedu.function.documentflow.model.NotifyProfile;
import com.nbcedu.function.documentflow.model.NotifyTime;
import com.nbcedu.function.documentflow.utils.MessageTester;
import com.nbcedu.function.documentflow.utils.NotifyTimeComparator;
import com.nbcedu.function.documentflow.utils.PortalMessageUtil;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.DocumentStatisticsVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.function.documentflow.vo.NotifyProfileVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.nbcedu.function.functionsupport.core.MobileMessageUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.function.functionsupport.util.PropertiesUtil;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

/**
 * <p>
 * DocumentBiz实现类
 * </p>
 * 
 * @author Wang Zhuoxuan Create at:2011-8-11 下午02:33:09
 */
public class DocumentBizImpl extends DocumentFlowBaseBizImpl implements DocumentBiz {

	private static Logger logger = Logger.getLogger(DocumentBizImpl.class);
	private DocumentSourceBiz documentSourceBiz;
	private DocumentTaskBiz documentTaskBiz;
	private CommentBiz commentBiz;
	private NotifyProfileBiz notifyProfileBiz;
	private SchedulerBiz schedulerBiz;

	private BaseClient baseClient = new BaseClient();

	@Override
	public String addDocument(DocumentVO documentVo) {
		Document document = new Document();
		document.setAuthorId(documentVo.getAuthorId());
		document.setTitle(documentVo.getTitle());
		document.setNotifyProfileId(documentVo.getNotifyProfileId());
		document.setDocumentSourceId(documentVo.getDocumentSourceId());
		document.setPeriod(documentVo.getPeriod());
		document.setStatus(Integer.parseInt(documentVo.getStatus()));
		document.setInsertTime(new Date());
		
		try {
			if (document.getStatus() == 1) {
				document.setPublishTime(new Date());
				if (!"-1".equals(document.getPeriod())) {
					document.setExpireTime(calExpireTime(document.getPeriod(), 
							document.getPublishTime(), documentVo.getExpireTime()));
				}
			} else {
				if ("0".equals(document.getPeriod())) {
					document.setExpireTime(StringUtil.convertStringToDateTime(documentVo.getExpireTime()));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (documentVo.getAttachments() != null) {
			document.setAttachments(documentVo.getAttachments());
		}
		if (!StringUtil.isBlank(documentVo.getContent())) {
			document.setContent(documentVo.getContent());
		}
		if (!StringUtil.isBlank(documentVo.getNotifyContent())) {
			document.setNotifyContent(documentVo.getNotifyContent());
		}
		if (documentVo.getReceiverIds() != null && documentVo.getReceiverIds().length > 0) {
			List<String> receivers = Arrays.asList(documentVo.getReceiverIds());
			Set<String> receiverSet = new HashSet<String>();
			receiverSet.addAll(receivers);
			List<String> receiverList = new ArrayList<String>();
			for (String receiver : receiverSet) {
				NbcUcTeacher teacher = baseClient.queryTeacher(1, receiver);
				//避免在进行选择时，将组织结构树中的组织节点选中，进行人员校验（比较耗费性能）
				if (teacher != null) {
					logger.info(teacher.getName());
					receiverList.add(receiver);
				}
			}
			document.setReceiverIds(receiverList);
		}
		
		if (documentVo.getNotifierIds() != null) {
			List<String> notifiers = Arrays.asList(documentVo.getNotifierIds());
			Set<String> notifierSet = new HashSet<String>();
			notifierSet.addAll(notifiers);
			List<String> notifierList = new ArrayList<String>();
			for (String notifier : notifierSet) {
				NbcUcTeacher teacher = baseClient.queryTeacher(1, notifier);
				//避免在进行选择时，将组织结构树中的组织节点选中，进行人员校验（比较耗费性能）
				if (teacher != null) {
					notifierList.add(notifier);
				}
			}
			document.setNotifierIds(notifierList);
		}
		getHibernateDao().create(document);

		//如果为发布操作，启动定时任务
		if (document.getStatus() == 1) {
			try {
				if (document.getNotifierIds() != null) {
					startNotifyScheduler(document);
				}
				startExpireScheduler(document);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return document.getId();
	}

	@Override
	public void addDocumentReceivedUser(DocumentVO documentVo, String user) {
		Document document = (Document) getHibernateDao().get(Document.class, documentVo.getId());
		List<String> receivedList = document.getReceivedIds();
		if (receivedList == null) {
			receivedList = new ArrayList<String>();
		}
		if(receivedList.isEmpty()||!receivedList.contains(user)){
			if(document.getReceiverIds().contains(user)){
				receivedList.add(user);
			}
		}
		document.setReceivedIds(receivedList);
		getHibernateDao().update(document);
	}

	@Override
	public DocumentVO findDocument(Serializable id) {
		Document document = (Document) getHibernateDao().get(Document.class, id);
		DocumentVO documentVo = new DocumentVO();
		documentVo.setId(document.getId());
		documentVo.setAuthorId(document.getAuthorId());
		
		if (document.getNotifierIds() != null && document.getNotifierIds().size() > 0) {
			documentVo.setNotifierIds(document.getNotifierIds().toArray(
					new String[document.getNotifierIds().size()]));
		}
		
		if (document.getReceiverIds() != null && document.getReceiverIds().size() > 0) {
			documentVo.setReceiverIds(document.getReceiverIds().toArray(
					new String[document.getReceiverIds().size()]));
			
			List<String> allNames = new ArrayList<String>();
			
			// 创建未处理人员数据
			documentVo.setUnhandledNames(unhandledUsers(document.getId()));
//			
//			// 创建已处理人员数据
			documentVo.setReceivedNames(receivedNames(document.getReceivedIds()));
			documentVo.setaLLDocNames(allNames);
		}
		
		/** 转发人员 **/
		//转发
		if (document.getForwardings() != null && document.getForwardings().size() > 0) {
			Map<String, List<String>> forwardMap = new HashMap<String, List<String>>();
			
			List<String> handledForwardsUserIds = documentTaskBiz.findDocumentTaskhandledUsersForwards(document.getId());
			List<String> allForwardsUserIds = documentTaskBiz.findDocumentTaskForwardsUsers(document.getId());
			documentVo.setForwardingIds(allForwardsUserIds.toArray(new String[allForwardsUserIds.size()]));
			
			for (Forwarding forward : document.getForwardings()) {
				String forwardPid = forward.getForwardingUser();
				String forwardUser = baseClient.queryTeacher(1, forwardPid).getName();
				if(forwardPid!=null && forward.getReceivers().size()>0){
					List<String> forwardNames = new ArrayList<String>();
					for(String pid : forward.getReceivers()){
						String teaName = baseClient.queryTeacher(1, pid).getName();
						if(handledForwardsUserIds.contains(pid)){
							teaName += "√";
							forwardNames.add(0, teaName);
						}else{
							forwardNames.add(teaName);
						}
						
					}
					forwardMap.put(forwardUser, forwardNames);
				}
			}
			documentVo.setForwarders(forwardMap);
		}
		
		if (document.getPublishTime() != null) {
			documentVo.setPublishTime(StringUtil.getDateTimeString(document.getPublishTime()));
		}
		if (document.getExpireTime() != null) {
			documentVo.setExpireTime(StringUtil.getDateTimeString(document.getExpireTime()));
		}

		documentVo.setTitle(document.getTitle());
		documentVo.setAttachments(document.getAttachments());
		documentVo.setContent(document.getContent());
		documentVo.setComments(commentBiz.findComments(document.getId()));
		documentVo.setNotifyProfileName(notifyProfileBiz.findNotifyProfile(document.getNotifyProfileId())
				.getProfileName());

		if (!StringUtil.isBlank(document.getDocumentSourceId())) {
			String documentSourceName = ((DocumentSource) documentSourceBiz.findById(DocumentSource.class,
					document.getDocumentSourceId())).getDisplayName();
			documentVo.setDocumentSourceName(documentSourceName);
		}
		NbcUcTeacher teacher = baseClient.queryTeacher(1, document.getAuthorId());
		documentVo.setAuthorName(teacher.getName());
		documentVo.setStatus(Document.Status.getStatusById(document.getStatus()).getStr());

		return documentVo;
	}

	@Override
	public DocumentVO findMyDocument(Serializable id) {
		Document document = (Document) getHibernateDao().get(Document.class, id);
		DocumentVO documentVo = new DocumentVO();

		documentVo.setId(document.getId());
		documentVo.setTitle(document.getTitle());
		documentVo.setAttachments(document.getAttachments());
		documentVo.setContent(document.getContent());
		documentVo.setIsEditable(isEditable(document));
		documentVo.setStatus(String.valueOf(document.getStatus()));
		if (documentVo.getIsEditable()) {
			//如果发文单位已选择，则选择相应的值赋给documentVo对象的相应属性，否则将默认值赋给documentVo对象的相应属性
			if (!"-1".equals(document.getDocumentSourceId())) {
				DocumentSource documentSource = ((DocumentSource) documentSourceBiz.findById(
						DocumentSource.class, document.getDocumentSourceId()));
				//如果所选择的发文单位已被删除，则将默认值赋给documentVo对象的相应属性，否则将保存的相应的值赋给documentVo对象的相应属性
				if ("0".equals(documentSource.getStatus())) {
					documentVo.setDocumentSourceName("--请选择--");
					documentVo.setDocumentSourceId("-1");
				} else {
					documentVo.setDocumentSourceName(documentSource.getDisplayName());
					documentVo.setDocumentSourceId(document.getDocumentSourceId());
				}
			} else {
				documentVo.setDocumentSourceName("--请选择--");
				documentVo.setDocumentSourceId("-1");
			}
			
			NotifyProfile notifyProfile = (NotifyProfile) notifyProfileBiz.findById(NotifyProfile.class, document.getNotifyProfileId());
			if ("0".equals(notifyProfile.getStatus())) {
				NotifyProfileVO notifyProfileVo = notifyProfileBiz.findNotifyProfileDefault();
				documentVo.setNotifyProfileId(notifyProfileVo.getId());
				documentVo.setNotifyProfileName(notifyProfileVo.getProfileName());
			} else {
				documentVo.setNotifyProfileId(document.getNotifyProfileId());
				documentVo.setNotifyProfileName(notifyProfile.getProfileName());
			}
			
			if (document.getNotifierIds() != null && document.getNotifierIds().size() > 0) {
				documentVo.setNotifierIds(document.getNotifierIds().toArray(
						new String[document.getNotifierIds().size()]));
			}
			
			if (document.getReceiverIds() != null && document.getReceiverIds().size() > 0) {
				Map<String, String> receivers = new HashMap<String, String>();
				for (String receiverId : document.getReceiverIds()) {
					if (!StringUtil.isBlank(receiverId)) {
						receivers.put(receiverId, baseClient.queryTeacher(1, receiverId).getName());
					}
				}
				documentVo.setReceivers(receivers);
				documentVo.setReceiverIds(document.getReceiverIds().toArray(
						new String[document.getReceiverIds().size()]));
			}
			documentVo.setNotifyContent(document.getNotifyContent());
			if (document.getExpireTime() == null) {
				documentVo.setExpireTime(StringUtil.getDateTimeString(new Date()));
			} else {
				documentVo.setExpireTime(StringUtil.getDateTimeString(document.getExpireTime()));
			}
			documentVo.setPeriod(document.getPeriod());
			if (document.getNotifierIds() != null) {
				Map<String, String> notifiers = new HashMap<String, String>();
				for (String notifierId : document.getNotifierIds()) {
					if (!StringUtil.isBlank(notifierId)) {
						notifiers.put(notifierId, baseClient.queryTeacher(1, notifierId).getName());
					}
				}
				documentVo.setNotifiers(notifiers);
				documentVo.setNotifierIds(document.getNotifierIds().toArray(
						new String[document.getNotifierIds().size()]));
			}
			
		} else {///如果不能编辑
			documentVo.setDocumentSourceId(document.getDocumentSourceId());
			documentVo.setDocumentSourceName(documentSourceBiz.findDocumentSource(document.getDocumentSourceId()).getDisplayName());
			
			if (document.getPublishTime() != null) {
				documentVo.setPublishTime(StringUtil.getDateTimeString(document.getPublishTime()));
			}
			if (document.getExpireTime() != null) {
				documentVo.setExpireTime(StringUtil.getDateTimeString(document.getExpireTime()));
			}

			documentVo.setComments(commentBiz.findComments(document.getId()));
			NbcUcTeacher teacher = baseClient.queryTeacher(1, document.getAuthorId());
			documentVo.setAuthorName(teacher.getName());
			
			documentVo.setNotifyProfileName(notifyProfileBiz.findNotifyProfile(document.getNotifyProfileId())
					.getProfileName());
			
			if (document.getNotifierIds() != null && document.getNotifierIds().size() > 0) {
				documentVo.setNotifierIds(document.getNotifierIds().toArray(
						new String[document.getNotifierIds().size()]));
			}
			
			if (document.getReceiverIds() != null && document.getReceiverIds().size() > 0) {
				documentVo.setReceiverIds(document.getReceiverIds().toArray(
						new String[document.getReceiverIds().size()]));
				
				List<String> allNames = new ArrayList<String>();
				
				// 创建未处理人员数据
				List<String> unhandledUserIds = documentTaskBiz.findDocumentTaskUnhandledUsers(document
						.getId());
				if (unhandledUserIds != null && unhandledUserIds.size() > 0) {
					List<String> unhandledNames = new ArrayList<String>();
					for (String pid : unhandledUserIds) {
						String name = baseClient.queryTeacher(1, pid).getName();
						unhandledNames.add(name);
						
						allNames.add(name);
					}
					documentVo.setUnhandledNames(unhandledNames);
				}
				// 创建已处理人员数据
				List<String> receivedUserIds = document.getReceivedIds();
				if (receivedUserIds != null && receivedUserIds.size() > 0) {
					List<String> receivedNames = new ArrayList<String>();
					for (String pid : receivedUserIds) {
						String name = baseClient.queryTeacher(1, pid).getName();
						receivedNames.add(name);
						
						allNames.add(0, name+"√");
					}
					documentVo.setReceivedNames(receivedNames);
				}
				
				documentVo.setaLLDocNames(allNames);
			}
			
			//转发
			if (document.getForwardings() != null && document.getForwardings().size() > 0) {
				Map<String, List<String>> forwardMap = new HashMap<String, List<String>>();
				
				List<String> handledForwardsUserIds = documentTaskBiz.findDocumentTaskhandledUsersForwards(document.getId());
				List<String> allForwardsUserIds = documentTaskBiz.findDocumentTaskForwardsUsers(document.getId());
				documentVo.setForwardingIds(allForwardsUserIds.toArray(new String[allForwardsUserIds.size()]));
				
				for (Forwarding forward : document.getForwardings()) {
					String forwardPid = forward.getForwardingUser();
					String forwardUser = baseClient.queryTeacher(1, forwardPid).getName();
					if(forwardPid!=null && forward.getReceivers().size()>0){
						List<String> forwardNames = new ArrayList<String>();
						for(String pid : forward.getReceivers()){
							String teaName = baseClient.queryTeacher(1, pid).getName();
							if(handledForwardsUserIds.contains(pid)){
								teaName += "√";
								forwardNames.add(0, teaName);
							}else{
								forwardNames.add(teaName);
							}
						}
						
						forwardMap.put(forwardUser, forwardNames);
					}
				}
				documentVo.setForwarders(forwardMap);
				
				
			}
			documentVo.setStatus(Document.Status.getStatusById(document.getStatus()).getStr());
		}
		documentVo.setOutTime(document.getExpireTime().before(new Date()));
		return documentVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentVO> findDocuments(String authorId, PagerUtils pagerUtils) {
		List<DocumentVO> documentVoList = new ArrayList<DocumentVO>();
		NbcUcTeacher teacher = baseClient.queryTeacher(1, authorId);
		
		List<Document> documentList = (List<Document>) getHibernateDao().retrievePageByQuery(
				"SELECT DISTINCT c.document FROM Comment c WHERE c.document.authorId = ? AND c.document.status = 1 ORDER BY "
						+ "c.createTime DESC", new Object[] {authorId}, pagerUtils);
		for (Document document : documentList) {
			Comment comment = commentBiz.findCommentLatest(document);
			DocumentVO documentVo = new DocumentVO();
			documentVo.setId(document.getId());
			documentVo.setTitle(StringUtil.split(document.getTitle(), 20));
			documentVo.setLongTitle(document.getTitle());
			documentVo.setAuthorName(teacher.getName());
			documentVo.setPublishTime(StringUtil.getDateTimeString(document.getPublishTime()));
			documentVo.setLastComment(comment);
			teacher = baseClient.queryTeacher(1, comment.getReplierId());
			documentVo.setLastReplier(teacher.getName());
			documentVo.setCommentReplyTime(StringUtil.getDateTimeString(comment.getCreateTime()));
			
			documentVoList.add(documentVo);
		}

		return documentVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentVO> findDocuments(String authorId, QueryConditionVO queryConditionVo,
			PagerUtils pagerUtils) {
		List<DocumentVO> documentVoList = new ArrayList<DocumentVO>();
		Map<String, Object> queryMap = generateHandledQuery(queryConditionVo, authorId);
		List<Document> documentList = (List<Document>) getHibernateDao().retrievePageByQuery(
				queryMap.get("queryStr").toString(), ((List<Object>) queryMap.get("params")).toArray(),
				pagerUtils);
		//documentList.get(0).getReceivedIds().get(0);
		// 将PO list转化为VO list
		for (Document document : documentList) {
			//System.out.println(document.getReceivedIds().get(0)+"ww");
			DocumentVO documentVo = new DocumentVO();
			documentVo.setId(document.getId());
			if(document.getExpireTime()!=null){
				documentVo.setExpireTime(StringUtil.getDateTimeString(document.getExpireTime()));
			}
			documentVo.setTitle(StringUtil.split(document.getTitle(), 11));
			documentVo.setLongTitle(document.getTitle());
			Comment comment = commentBiz.findCommentLatest(document);
			if (comment != null) {
				documentVo.setLastComment(comment);
				documentVo.setLastReplier(baseClient.queryTeacher(1, comment.getReplierId()).getName());
				documentVo.setLastCommentContent(StringUtil.split(comment.getContent(), 5));
				documentVo.setCommentReplyTime(StringUtil.getDateTimeString(comment.getCreateTime()));
			}
			documentVo.setStatus(Document.Status.getStatusById(document.getStatus()).getStr());
			if (!"-1".equals(document.getDocumentSourceId())) {
				documentVo.setDocumentSourceName(((DocumentSource) documentSourceBiz.findById(
						DocumentSource.class, document.getDocumentSourceId())).getDisplayName());
			}
			if (document.getPublishTime() != null) {
				documentVo.setPublishTime(StringUtil.getDateTimeString(document.getPublishTime()));
			}
			documentVo.setHandledCount(documentTaskBiz.findDocumentTaskCountHandled(document));
			documentVo.setUnhandledCount(documentTaskBiz.findDocumentTaskCountUnHandled(document));		
			// 创建未处理人员数据
			documentVo.setUnhandledNames(unhandledUsers(document.getId()));
			// 创建已处理人员数据
			documentVo.setReceivedNames(receivedNames(document.getReceivedIds()));
			documentVoList.add(documentVo);
		}
		return documentVoList;
	}
	private List<String> receivedNames(List<String> receivedUserIds ){
//	List<String> receivedUserIds = document.getReceivedIds();
	List<String> receivedNames = new ArrayList<String>();
	if (receivedUserIds != null && receivedUserIds.size() > 0) {
		for (String pid : receivedUserIds) {
			String name = baseClient.queryTeacher(1, pid).getName();
			receivedNames.add(name);
		}
	}else{
		receivedNames.add("无");
	}
	return receivedNames;
}
	private List<String> unhandledUsers(String id){
		List<String> unhandledUserIds = documentTaskBiz.findDocumentTaskUnhandledUsers(id);
		List<String> unhandledNames = new ArrayList<String>();
		if (unhandledUserIds != null && unhandledUserIds.size() > 0) {
			for (String pid : unhandledUserIds) {
				String name = baseClient.queryTeacher(1, pid).getName();
				unhandledNames.add(name);
			}
		}else{
			unhandledNames.add("无");
		}
		
		return unhandledNames;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentVO> findMyDocumentsFinished(String authorId, PagerUtils pagerUtils) {
		List<DocumentVO> documentVoList = new ArrayList<DocumentVO>();
		//User user = userBiz.findUserByPid(authorId); TODO:修改了
		
		List<Object[]> documentList = (List<Object[]>) getHibernateDao().retrievePageByQuery(
				"SELECT d.id, d.title, d.authorId, d.publishTime, dt.handlingTime FROM Document d, "
						+ "DocumentTask dt WHERE d.authorId = ? AND d.status = ? AND d.id = dt.documentId "
						+ "AND dt.isHandled = ? GROUP BY d.id ORDER BY dt.handlingTime DESC",
				new Object[] {authorId, 2, true}, pagerUtils);

		Iterator it = documentList.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			DocumentVO documentVo = new DocumentVO();
			documentVo.setId((String) obj[0]);
			documentVo.setTitle(StringUtil.split((String) obj[1], 20));
			documentVo.setLongTitle((String) obj[1]);
			
			NbcUcTeacher teacher = baseClient.queryTeacher(1, (String) obj[2]);
			documentVo.setAuthorName(teacher.getName());
			documentVo.setPublishTime(StringUtil.getDateTimeString((Date) obj[3]));
			documentVo.setEndingTime(StringUtil.getDateTimeString((Date) obj[4]));

			documentVoList.add(documentVo);
		}

		return documentVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentVO> findMyDocumentsExpired(String authorId, PagerUtils pagerUtils) {
		List<DocumentVO> documentVoList = new ArrayList<DocumentVO>();
		List<Document> documentList = (List<Document>) getHibernateDao().retrievePageByQuery(
				"FROM Document d WHERE d.authorId = ? AND d.status = ? ORDER BY d.expireTime DESC",
				new Object[] {authorId, 3}, pagerUtils);
		encapeVo(authorId, documentVoList, documentList);
		return documentVoList;
	}
	/**
	 *封装vo
	 * @param authorId
	 * @param documentVoList
	 * @param documentList
	 * @author xuechong
	 */
	private void encapeVo(String authorId, List<DocumentVO> documentVoList,
			List<Document> documentList) {
		NbcUcTeacher teacher = baseClient.queryTeacher(1, authorId);
		
		for (Document document : documentList) {
			DocumentVO documentVo = new DocumentVO();
			documentVo.setId(document.getId());
			documentVo.setTitle(StringUtil.split(document.getTitle(), 20));
			documentVo.setLongTitle(document.getTitle());
			documentVo.setAuthorName(teacher.getName());
			documentVo.setPublishTime(StringUtil.getDateTimeString(document.getPublishTime()));
			documentVo.setExpireTime(StringUtil.getDateTimeString(document.getExpireTime()));
			if (!"-1".equals(document.getDocumentSourceId())) {
				documentVo.setDocumentSourceName(((DocumentSource) documentSourceBiz.findById(
						DocumentSource.class, document.getDocumentSourceId())).getDisplayName());
			}
			// 创建未处理人员数据
			documentVo.setUnhandledNames(unhandledUsers(document.getId()));
//			// 创建已处理人员数据
			documentVo.setReceivedNames(receivedNames(document.getReceivedIds()));
			documentVoList.add(documentVo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findDocumentCount(QueryConditionVO queryConditionVo) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentCountQuery(queryConditionVo);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(),
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int findDocumentFinishedTaskCount(QueryConditionVO queryConditionVo, boolean bool) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentCountQuery2(queryConditionVo, bool);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(),
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findDocumentExpiredCount(QueryConditionVO queryConditionVo) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentExpiredCountQuery(queryConditionVo);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(),
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void modifyDocumentStatus(String id, int status) {
		getHibernateDao().update("UPDATE Document d SET d.status = ? WHERE d.id = ?",
				new Object[] {status, id});
	}
	@Override
	public void modifyDocumentShut(String id, int shut) {
		getHibernateDao().update("UPDATE Document d SET d.shut = ? WHERE d.id = ?",
				new Object[] {shut, id});
	}

	@Override
	public void modifyDocument(DocumentVO documentVo) {
		BaseClient client = new BaseClient();
		Document document = (Document) findById(Document.class, documentVo.getId());
		
		document.setTitle(documentVo.getTitle());
		document.setPeriod(documentVo.getPeriod());
		document.setDocumentSourceId(documentVo.getDocumentSourceId());
		document.setNotifyProfileId(documentVo.getNotifyProfileId());
		document.setContent(documentVo.getContent());
		document.setNotifyContent(documentVo.getNotifyContent());
		
		if (!StringUtil.isBlank(documentVo.getStatus())) {
			document.setStatus(Integer.parseInt(documentVo.getStatus()));
			document.setPublishTime(new Date());
		}
		try {
			if (document.getStatus() == 1) {
				if (!"-1".equals(document.getPeriod())) {
					document.setExpireTime(calExpireTime(document.getPeriod(), 
							document.getPublishTime(), documentVo.getExpireTime()));
				}
			} else {
				if ("0".equals(document.getPeriod())) {
					document.setExpireTime(StringUtil.convertStringToDateTime(documentVo.getExpireTime()));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (documentVo.getAttachments() != null) {
			document.setAttachments(documentVo.getAttachments());
		}
		
		if (documentVo.getReceiverIds() != null && documentVo.getReceiverIds().length > 0) {
			List<String> receivers = Arrays.asList(documentVo.getReceiverIds());
			Set<String> receiverSet = new HashSet<String>();
			receiverSet.addAll(receivers);
			List<String> receiverList = new ArrayList<String>();
			for (String receiver : receiverSet) {
				NbcUcTeacher teacher = client.queryTeacher(1, receiver);
				if (teacher != null) {
					receiverList.add(receiver);
				}
			}
			document.setReceiverIds(receiverList);
		}
		
		if (documentVo.getNotifierIds() != null && documentVo.getNotifierIds().length > 0) {
			List<String> notifiers = Arrays.asList(documentVo.getNotifierIds());
			Set<String> notifierSet = new HashSet<String>();
			notifierSet.addAll(notifiers);
			List<String> notifierList = new ArrayList<String>();
			for (String notifier : notifierSet) {
				NbcUcTeacher teacher = client.queryTeacher(1, notifier);
				if (teacher != null) {
					notifierList.add(notifier);
				}
			}
			document.setNotifierIds(notifierList);
		}

		getHibernateDao().update(document);

		if (document.getStatus() == 1) {
			try {
				if (document.getNotifierIds() != null) {
					startNotifyScheduler(document);
				}
				startExpireScheduler(document);
			} catch (ParseException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean hasFinished(DocumentVO documentVo) {
		for (DocumentTask task : documentTaskBiz.findDocumentTasks(documentVo.getId())) {
			if (!task.getIsHandled()) {
				return false;
			}
		}
		return true;
	}
	@Override
	public int removeDocuments(String documentIds) {
		String[] documentIdArray = documentIds.split(",");
		int num = documentIdArray.length;

		for (String documentId : documentIdArray) {
			getHibernateDao().delete(Document.class, documentId);
		}

		return num;
	}

	/**
	 * 启动短信定时提醒任务
	 * @param document 要启动任务所属的公文
	 * @throws ParseException 如果日期转换出错，则抛出该异常
	 */
	private void startNotifyScheduler(Document document) throws ParseException {
		BaseClient client = new BaseClient();
		NotifyProfile profile = (NotifyProfile) notifyProfileBiz.findById(NotifyProfile.class, document.getNotifyProfileId());
		NotifyTime[] notifyTimes = profile.getNotifyTimes().toArray(new NotifyTime[profile.getNotifyTimes().size()]);
		Arrays.sort(notifyTimes, new NotifyTimeComparator());
		Map<String, String> paramMap = new HashMap<String, String>();
		StringBuilder notifierIds = new StringBuilder();
		String mode = PropertyUtils.readValue("appconfig.properties", "function.documentFlow.mode");
		// documentVo = documentBiz.findDocument(documentVo.getId());
		DateTime publishTime = new DateTime(StringUtil.getDateTimeString(document.getPublishTime()));
		DateTime expireTime = new DateTime(StringUtil.getDateTimeString(document.getExpireTime()));
		DateTime excuteTime = null;
		// 拼接要发送的手机号码
		for (String pid : document.getNotifierIds()) {
			notifierIds.append(client.queryTeacher(1, pid).getTelephone() + ",");
		}
		notifierIds.deleteCharAt(notifierIds.lastIndexOf(","));
		String target = notifierIds.toString();
		String content = document.getNotifyContent() + "[" + document.getTitle() + "]";

		// paramMap.put("target", target);
		paramMap.put("docId", document.getId());
		paramMap.put("content", content);

		// 根据所选的提醒配置的提醒时间迭代启动任务调度器
		for (int i = 0; i < notifyTimes.length; i++) {
			if (notifyTimes[i].getOrderId() == 1) {
				logger.info("发送的手机号码：" + target + ",发送的内容：" + content);
				if (!"dev".equals(mode)) {
					MobileMessageUtil sms = SupportManager.getMobileMessageUtil();
					sms.sendMobileMessage(target, content);
					
					MessageTester.sendMessage(target, content);
					//System.out.println("SEND:"+"发送的手机号码：" + target + ",发送的内容：" + content);
				}
			} else if (notifyTimes[i].getOrderId() == 2) {
				if ("true".equals(mode)) {
					//生产环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 1, 0, 0, DateTime.DayOverflow.Abort);
				} else {
					//测试环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 0, 1, 0, DateTime.DayOverflow.Abort);
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			} else if (notifyTimes[i].getOrderId() == 3) {
				if ("true".equals(mode)) {
					//生产环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 24, 0, 0, DateTime.DayOverflow.Abort);
				} else {
					//测试环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 0, 1, 0, DateTime.DayOverflow.Abort);
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			} else if (notifyTimes[i].getOrderId() == 4) {
				if ("true".equals(mode)) {
					//生产环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 72, 0, 0, DateTime.DayOverflow.Abort);
				} else {
					//测试环境计算方式
					excuteTime = publishTime.plus(0, 0, 0, 0, 1, 0, DateTime.DayOverflow.Abort);
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			} else if (notifyTimes[i].getOrderId() == 5) {
				if ("true".equals(mode)) {
					//生产环境计算方式
					excuteTime = expireTime.minus(0, 0, 0, 48, 0, 0, DateTime.DayOverflow.Abort);
				} else {
					//测试环境计算方式
					excuteTime = expireTime.minus(0, 0, 0, 0, 1, 0, DateTime.DayOverflow.Abort);
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			} else if (notifyTimes[i].getOrderId() == 6) {
				if ("true".equals(mode)) {
					//生产环境计算方式
					excuteTime = expireTime.minus(0, 0, 0, 24, 0, 0, DateTime.DayOverflow.Abort);
				} else {
					//测试环境计算方式
					excuteTime = expireTime.minus(0, 0, 0, 0, 1, 0, DateTime.DayOverflow.Abort);
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			}else if (notifyTimes[i].getOrderId() == 7) {// 过期前提醒
				long msDiff = Math.abs(expireTime.getMilliseconds(TimeZone.getDefault())-publishTime.getMilliseconds(TimeZone.getDefault()));
				if (msDiff>1000L*60L*60L*24L) {// 时间在一天内
					String pushTimeStr = "$ymd $h:00:00";
					pushTimeStr = pushTimeStr.replace("$ymd", expireTime.format("YYYY-MM-DD"));
					if (expireTime.getHour()>=12 && expireTime.getHour()<17 ){
						pushTimeStr = pushTimeStr.replace("$h", dayToStr(expireTime.getHour()-4));
						excuteTime = new DateTime(pushTimeStr);
					}else if(expireTime.getHour()>=17){
						pushTimeStr = pushTimeStr.replace("$h", "13");
						excuteTime = new DateTime(pushTimeStr);
					}else if(expireTime.getHour()<12){
						pushTimeStr = pushTimeStr.replace("$h", "17");
						//前一天下午5点
						excuteTime = new DateTime(pushTimeStr).minus(0, 0, 0, 24, 0, 0, DateTime.DayOverflow.Abort);
					}					
				}else{ 
					if(expireTime.getHour()>=8 && expireTime.getHour()<=20){
						excuteTime = expireTime.minus(0, 0, 0, 24, 0, 0, DateTime.DayOverflow.Abort);
					}else {
						String pushTimeStr = "$ymd $h:00:00";
							pushTimeStr = pushTimeStr.replace("$ymd", expireTime.format("YYYY-MM-DD"));
						if(expireTime.getHour()>20){
							pushTimeStr = pushTimeStr.replace("$h", "20");
						}else if(expireTime.getHour()<8){
							pushTimeStr = pushTimeStr.replace("$h", "08");
						}
							excuteTime = new DateTime(pushTimeStr).minus(0, 0, 0, 24, 0, 0, DateTime.DayOverflow.Abort);
					}
					
				}
				schedulerBiz.schedule("notifyingJobBean", paramMap, StringUtil
						.convertStringToDateTime(excuteTime.toString()));
			}

		}
	}
	private String dayToStr(int day){
		if (day>9){
			return String.valueOf(day);
		}else{
			return "0" + day;
		}
	}

	/** 
	 * 启动公文定时过期任务
	 * 
	 * @param document 要执行定时过期任务的公文
	 */ 
	private void startExpireScheduler(Document document) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("docId", document.getId());
		schedulerBiz.schedule("expiringDocumentJobBean", paramMap, document.getExpireTime());
	}
	
	/**
	 * 返回按指定条件查询过期公文数量的查询字符串和参数的Map
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @return 按指定条件查询公文数量的查询字符串和参数的Map
	 * @throws ParseException 如果参数中字符串转换时间出错
	 */
	private Map<String, Object> generateDocumentExpiredCountQuery(QueryConditionVO queryConditionVo)
			throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();

		query.append("SELECT COUNT(*) FROM Document d WHERE d.expireTime <= ?");
		params.add(new Date());

		if (!StringUtil.isBlank(queryConditionVo.getStarting())) {
			query.append(" AND d.publishTime >= ?");
			params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
		}
		if (!StringUtil.isBlank(queryConditionVo.getEnding())) {
			query.append(" AND d.publishTime <= ?");
			params.add(StringUtil.convertStringToDate(queryConditionVo.getEnding()));
		}

		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);

		return queryMap;
	}

	/**
	 * 返回按指定条件查询公文数量的查询字符串和参数的Map
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @return 按指定条件查询公文数量的查询字符串和参数的Map
	 * @throws ParseException 如果参数中字符串转换时间出错
	 */
	private Map<String, Object> generateDocumentCountQuery(QueryConditionVO queryConditionVo)
			throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();

		query.append("SELECT COUNT(*) FROM Document d");

		if (!StringUtil.isBlank(queryConditionVo.getStarting())
				|| !StringUtil.isBlank(queryConditionVo.getEnding())) {
			query.append(" WHERE");

			if (!StringUtil.isBlank(queryConditionVo.getStarting())
					&& StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime >= ?");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
			} else if (StringUtil.isBlank(queryConditionVo.getStarting())
					&& !StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime <= ?");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getEnding()));
			} else if (!StringUtil.isBlank(queryConditionVo.getStarting())
					&& !StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime >= ? AND d.publishTime <= ?");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
				params.add(StringUtil.convertStringToDate(queryConditionVo.getEnding()));
			}
		}

		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);

		return queryMap;
	}
	
	private Map<String, Object> generateDocumentCountQuery2(QueryConditionVO queryConditionVo, boolean bool)
	throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		query.append("SELECT COUNT(distinct dt.id ) FROM DocumentTask dt, Document d ");
	
		query.append(" WHERE dt.documentId=d.id and dt.isHandled=? ");
		params.add(bool);
		
		if (!StringUtil.isBlank(queryConditionVo.getStarting()) || !StringUtil.isBlank(queryConditionVo.getEnding())) {
			if (!StringUtil.isBlank(queryConditionVo.getStarting())) {
				query.append(" and d.publishTime >= ? ");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
			}
			if (!StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" and d.publishTime <= ? ");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getEnding()));
			}
		}
		
		if(!bool){ //未处理，已过期
			query.append(" and d.status=3 ");
		}
			
		
		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);
		
		return queryMap;
	}


	/**
	 * 生成已处理公文的查询条件
	 * @param queryConditionVo 查询条件的值对象
	 * @param authorId 指定用户的唯一标识
	 * @return 已处理公文的查询条件
	 */
	private Map<String, Object> generateHandledQuery(QueryConditionVO queryConditionVo, String authorId) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();

		query.append("FROM Document d WHERE d.authorId = ?");
		params.add(authorId);

		if (!StringUtil.isBlank(queryConditionVo.getDocumentName())) {
			query.append(" AND d.title LIKE ?");
			params.add("%" + queryConditionVo.getDocumentName() + "%");
		}
		if (!StringUtil.isBlank(queryConditionVo.getDocumentStatus())) {
			query.append(" AND d.status = ?");
			params.add(Integer.parseInt(queryConditionVo.getDocumentStatus()));
		}
		if (!"-1".equals(queryConditionVo.getDocumentSourceId()) && !StringUtil.isBlank(queryConditionVo.getDocumentSourceId())) {
			query.append(" AND d.documentSourceId = ?");
			params.add(queryConditionVo.getDocumentSourceId());
		}
		if (!StringUtil.isBlank(queryConditionVo.getStarting())) {
			query.append(" AND d.publishTime > ?");
			try {
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtil.isBlank(queryConditionVo.getEnding())) {
			query.append(" AND d.publishTime < ?");
			try {
				DateTime ending = new DateTime(queryConditionVo.getEnding());
				params.add(StringUtil.convertStringToDate(ending.plusDays(1).toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		query.append(" ORDER BY d.insertTime DESC, d.publishTime DESC, d.status");

		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);

		return queryMap;
	}

	/**
	 * 当前公文是否可编辑
	 * 
	 * @param document 要进行判断的公文实体
	 * @return 如果可编辑返回true否则返回false
	 */
	private boolean isEditable(Document document) {
		if (document.getStatus() == 0) {
			return true;
		} else if (document.getStatus() == 1) {
			/*int i = ((Long) getHibernateDao().retrieve(
					"SELECT COUNT(*) FROM DocumentTask dt WHERE dt.documentId = ? AND dt.isHandled = ?",
					new Object[] {document.getId(), true}).get(0)).intValue();
			if (i == 0) {
				return true;
			}*/ //直接返回false，因为修改可能还会修改公文接收人，删除以前的接收人
			return false;
		}
		return false;
	}

	/** 
	 * 计算过期时间
	 * 
	 * @param period 有效期的值
	 * @param publishTime 发布时间
	 * @param expireTimeStr 过期时间的字符串表示
	 * @return 过期时间
	 * @throws ParseException 如果Date对象解析出错
	 */ 
	private Date calExpireTime(String period, Date publishTime, String expireTimeStr) throws ParseException {
		if ("0".equals(period)) {
			return StringUtil.convertStringToDateTime(expireTimeStr);
		} else {
			DateTime publish = new DateTime(StringUtil.getDateTimeString(publishTime));
			DateTime expireTime = null;
			String mode = PropertyUtils.readValue("appconfig.properties", "function.documentFlow.mode");
			if ("true".equals(mode)) {
				//生产环境计算方式
				expireTime = publish.plusDays(Integer.parseInt(period) * 30);
			} else {
				//测试环境计算方式
				expireTime = publish.plus(0, 0, 0, 0, Integer.parseInt(period), 0, DateTime.DayOverflow.Abort);
			}
			return StringUtil.convertStringToDateTime(expireTime.toString());
		}
	}
	
	

	@Override
	public DocumentStatisticsVO findDocumentStatistics() {
		DocumentStatisticsVO vo = new DocumentStatisticsVO();
		vo.setDocumentExpired(this.findMyDocumentStatusCount(3));//查询过期公文数
		vo.setDocumentFinished(this.findMyDocumentStatusCount(2));
		vo.setDocumentUnFinished(this.findMyDocumentStatusCount(1));
		List<Document> list = this.findMyDocumentTopFive(1, 5);
		return vo;
	}
	@Override
	public int findMyDocumentsFinishedCount(String authorId) {
		int result = 0;
		result = getHibernateDao().retrieveCountByQuery(
				"SELECT count(d.id) FROM Document d, DocumentTask dt WHERE " 
				+ "dt.handlerId = ? AND d.status = ? AND  dt.isHandled = ?  AND d.id = dt.documentId",
				new Object[] {authorId, 1, true}) ;

		return result;
	}
	@Override
	public int findMyDocumentsUnFinishedCount(String authorId) {
		int result = 0;
		result = (Integer) getHibernateDao().retrieveCountByQuery(
				"SELECT count(d.id)  FROM Document d, DocumentTask dt WHERE " 
				+ " dt.handlerId = ? AND d.status = ? AND dt.isHandled = ?  AND d.id = dt.documentId",
				new Object[] {authorId, 1, false} );
		
		return result;
	}
	@Override
	public int findMyDocumentsExpiredCount(String authorId) {
		int result = 0;
		result = (Integer) getHibernateDao().retrieveCountByQuery(
				"SELECT count(d.id)  FROM Document d, DocumentTask dt WHERE " 
				+ "dt.handlerId = ? AND d.status = ?  AND d.id = dt.documentId",
				new Object[] {authorId, 3} );
		
		return result;
	}
	/**
	 * 返回各个状态下的公文
	 */
	@Override
	public int findMyDocumentStatusCount(int status) {
		int result = 0;
		result = (Integer) getHibernateDao().retrieveCountByQuery(
				"SELECT count(d.id)  FROM Document d WHERE " 
				+ " d.status = ? ",
				new Object[] {status} );
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DocumentVO> findFlowingDocuments(final String pubPid,
			final PagerUtils pager, final Date start, final Date end ,final int shut) {
		List<DocumentVO> result = new ArrayList<DocumentVO>();
		final Date startDate = (start==null?new Date():start);
		final Date endDate ;
		if(end==null){
			endDate = new Date((System.currentTimeMillis()+1000l*60l*60l*24l*90l));//默认90天之内
		}else{endDate = end;}
		List<Document> docList =  this.getHibernateDao().getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				
				Criteria cri = session.createCriteria(Document.class);
				createFlowingCriteria(pubPid, startDate, endDate,shut, cri);
				cri.addOrder(Order.asc("expireTime"));
				cri.setFirstResult((pager.getPageIndex()-1)*pager.getPageSize());
				cri.setMaxResults(pager.getPageSize());
				return cri.list();
			}

			
		});
		Integer maxResults = (Integer) this.getHibernateDao().getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Criteria cri = session.createCriteria(Document.class);
				createFlowingCriteria(pubPid, startDate, endDate,shut, cri);
				cri.setProjection(Projections.count("id"));
				return cri.uniqueResult();
			}
		});
		
		this.encapeVo(pubPid, result, docList);
		
		this.getHibernateDao().buildPager(pager, maxResults);
		return result;
	}

	
	private void createFlowingCriteria(final String pubPid,
			Date startDate, Date endDate, int shut, Criteria cri) {
		cri.add(
			Restrictions.and(
				Restrictions.eq("authorId", pubPid),
				Restrictions.and(Restrictions.between("expireTime", startDate, endDate), 
						Restrictions.eq("status", 1))
				)
			
		).add(Restrictions.eq("shut", shut));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findMyDocumentTopFive(int status, int num) {
		List<Document> list = (List)this.getHibernateDao().retrievePageByQuery("FROM Document d WHERE " 
					+ " d.status = ? ", status, num);
		return list;
	}
	
	@Override
	public boolean removeDocumentById(final String id) {
		try {
			final String procName = 
				PropertiesUtil.findPropertieValue("appconfig.properties", "document_delproc");
			
			this.getHibernateDao().getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Connection connection = session.connection();
					CallableStatement cstmt;
					cstmt = connection.
						prepareCall("{CALL " + procName + "(?,?)}");
					cstmt.setString(1, id);
					cstmt.registerOutParameter(2, Types.VARCHAR);
					cstmt.executeQuery();
					return cstmt.getString(2);
				}
			});
			PortalMessageUtil.removeSingleMessage(id);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("删除公文时发生错误", e);
		}
		return false;
	}
	
	//////////////////////
	////GETTERS&SETTERS///
	//////////////////////
	public void setDocumentSourceBiz(DocumentSourceBiz documentSourceBiz) {
		this.documentSourceBiz = documentSourceBiz;
	}
	public void setDocumentTaskBiz(DocumentTaskBiz documentTaskBiz) {
		this.documentTaskBiz = documentTaskBiz;
	}
	public void setCommentBiz(CommentBiz commentBiz) {
		this.commentBiz = commentBiz;
	}
	public void setNotifyProfileBiz(NotifyProfileBiz notifyProfileBiz) {
		this.notifyProfileBiz = notifyProfileBiz;
	}
	public void setSchedulerBiz(SchedulerBiz schedulerBiz) {
		this.schedulerBiz = schedulerBiz;
	}
	public BaseClient getBaseClient() {
		return baseClient;
	}

	public void setBaseClient(BaseClient baseClient) {
		this.baseClient = baseClient;
	}

}
