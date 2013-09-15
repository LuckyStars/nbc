/*
 * @Title: DocumentTaskBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: DocumentTaskBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-15 下午02:14:20
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-15                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import hirondelle.date4j.DateTime;

import java.math.BigDecimal;
import java.text.NumberFormat;
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

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.common.utils.StringUtils;
import com.nbcedu.function.documentflow.biz.DocumentBiz;
import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.model.Document;
import com.nbcedu.function.documentflow.model.DocumentTask;
import com.nbcedu.function.documentflow.vo.DocumentTaskVO;
import com.nbcedu.function.documentflow.vo.DocumentVO;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.nbcedu.function.documentflow.vo.StatisticsVO;
import com.nbcedu.function.documentflow.utils.PortalMessageUtil;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.functionsupport.core.OAMessageUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcDepartment;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

/** 
 * <p>DocumentTaskBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-15 下午02:14:20
 */
public class DocumentTaskBizImpl extends DocumentFlowBaseBizImpl implements DocumentTaskBiz {
	private static final long serialVersionUID = 5406495724063737397L;
	
	private DocumentBiz documentBiz;
	private BaseClient baseClient;
	private DocumentSourceBiz documentSourceBiz;
	
	@Override
	public Integer addDocumentTask(DocumentVO documentVo, String type, String handlingUrl) {
		List<String> handlerIds;
		List<String> uidList = new ArrayList<String>();
		String authorUid = baseClient.queryUidPid(2, documentVo.getAuthorId());
		if (type.equals("0")) {
			handlerIds = Arrays.asList(documentVo.getReceiverIds());
		} else {
			handlerIds = Arrays.asList(documentVo.getForwardingIds());
		}
		
		Set<String> handlerSet = new HashSet<String>();
		handlerSet.addAll(handlerIds);
		
		List<DocumentTask> tasks = new ArrayList<DocumentTask>();
		
		for (String handlerId : handlerSet) {
			DocumentTask task = new DocumentTask();
			task.setDocumentId(documentVo.getId());
			task.setIsHandled(false);
			task.setHandlerId(handlerId);
			task.setType(type);
			tasks.add(task);
			
			String uid = baseClient.queryUidPid(2, handlerId);
			uidList.add(uid);
		}
		
		Integer tasknum = getHibernateDao().batchCreate(tasks, 20);
		
		if (handlingUrl != null) {
			String pushContent;
			if (StringUtils.isBlank(documentVo.getTitle())) {
				pushContent = "公文【" + documentBiz.findDocument(documentVo.getId()).getTitle() + "】，请查收！"; 
			} else {
				pushContent = "公文【" + documentVo.getTitle() + "】，请查收！";
			}
			
			try {
				handlingUrl = "documentFlow/viewUnhandledDocument.action?docId=" + documentVo.getId() + "&taskType=" + type;
				if(PortalMessageUtil.isPortalOK){ //门户消息推送
					PortalMessageUtil.sendMessage(documentVo.getId(), pushContent, pushContent, handlingUrl, (String[]) uidList.toArray(new String[uidList.size()]));
				}
				if(PortalMessageUtil.isIMOK){ //协同办公消息推送
					OAMessageUtil oAMessageUtil = SupportManager.getOAMessageUtil();
					oAMessageUtil.sendOAMessage(authorUid, uidList, pushContent);
					System.out.println("协同发送人：" + authorUid + ", 接收人：" + uidList);
				}
			} catch (Exception e) {
				System.out.println("消息推送异常...");
				e.printStackTrace();
			}
		}
		
		return tasknum;
	}
	@SuppressWarnings("unchecked")
	@Override
	public int findDocumentTasksCount(QueryConditionVO queryConditionVo,
			String handlerIdUid, boolean status){
		String handlerId = baseClient.queryUidPid(1, handlerIdUid);
		Map<String, Object> queryMap = generateHandledQuery(queryConditionVo, handlerId, status);
		
		return  getHibernateDao().retrieveCountByQuery(queryMap.get("queryStr").toString(), 
				((List<Object>) queryMap.get("params")).toArray());
	}
	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTasks(com.nbcedu.function.documentflow.vo.QueryConditionVO, com.nbcedu.common.utils.PagerUtils, java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentTaskVO> findDocumentTasks(QueryConditionVO queryConditionVo,
			PagerUtils pagerUtils, String handlerId, boolean status) {
		List<DocumentTaskVO> handleList = new ArrayList<DocumentTaskVO>();
		Map<String, Object> queryMap = generateHandledQuery(queryConditionVo, handlerId, status);
		
		List<Object[]> results = (List<Object[]>) getHibernateDao().retrievePageByQuery(queryMap.get("queryStr").toString(), 
				((List<Object>) queryMap.get("params")).toArray(), pagerUtils);
		
		Iterator it = results.iterator();
		DocumentVO documentVO;
		while (it.hasNext()) {
			Object[] result = (Object[]) it.next();
			DocumentTaskVO handled = new DocumentTaskVO();
			handled.setId(result[0].toString());
			NbcUcTeacher teacher = baseClient.queryTeacher(1, result[3].toString());
			handled.setAuthorName(teacher.getName());
			handled.setDocumentTitle(StringUtil.split(result[1].toString(), 11));
			handled.setLongTitle(result[1].toString());
			handled.setPublishTime(StringUtil.getDateTimeString((Date) result[2]));
			handled.setType(result[7].toString());
			int documentStatus = Integer.parseInt(result[4].toString());
			if (documentStatus == 0) {
				handled.setStatus("未发布");
			} else if (documentStatus == 1) {
				handled.setStatus("流转中");
			} else if (documentStatus == 2) {
				handled.setStatus("已结束");
			} else {
				handled.setStatus("已过期");
			}
			if (status) {
				handled.setHandlingTime(StringUtil.getDateTimeString((Date) result[5]));
			} else {
				handled.setExpireTime(StringUtil.getDateTimeString((Date) result[6]));
			}
			documentVO = documentBiz.findDocument(handled.getId());
			handled.setDocumentVO(documentVO);
			handled.setDocumentSourceDisplayName(documentSourceBiz.findDocumentSource(result[8].toString()).getDisplayName());
			
			handleList.add(handled);
		}
		return handleList;
	}
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTasks(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentTask> findDocumentTasks(String documentId) {
		return (List<DocumentTask>) getHibernateDao().retrieve("FROM DocumentTask dt WHERE dt.documentId = ? ", new Object[]{documentId});
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTaskType(java.lang.String, java.lang.String)
	 */
	@Override
	public String findDocumentTaskType(String docId, String handlerId) {
		DocumentTask task = (DocumentTask) getHibernateDao().retrieve(
				"FROM DocumentTask dt WHERE dt.documentId = ? AND dt.handlerId = ?", 
				new Object[]{docId, handlerId});
		return task.getType();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DocumentTask findDocumentTaskByHanderId(String docId, String handlerId) {
		List<DocumentTask> tasks =  (List<DocumentTask>) getHibernateDao().retrieve(
				"FROM DocumentTask dt WHERE dt.documentId = ? AND dt.handlerId = ?", 
				new Object[]{docId, handlerId});
		if(tasks!=null && tasks.size()>0){return tasks.get(0);}
		return null;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTaskCountHandled(com.nbcedu.function.documentflow.model.Document)
	 */
	@Override
	public int findDocumentTaskCountHandled(Document document) {
		return ((Long) getHibernateDao().get(
				"SELECT COUNT(*) FROM DocumentTask dt WHERE dt.documentId = ? AND dt.isHandled = ? ", 
				new Object[]{document.getId(), true})).intValue();
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTaskCountUnHandled(com.nbcedu.function.documentflow.model.Document)
	 */
	@Override
	public int findDocumentTaskCountUnHandled(Document document) {
		return ((Long) getHibernateDao().get(
				"SELECT COUNT(*) FROM DocumentTask dt WHERE dt.documentId = ? AND dt.isHandled = ? ", 
				new Object[]{document.getId(), false})).intValue();
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTasksStat(com.nbcedu.function.documentflow.vo.QueryConditionVO, com.nbcedu.common.utils.PagerUtils)
	 */
	@Override
	public List<StatisticsVO> findDocumentTasksStat(QueryConditionVO queryConditionVo, PagerUtils pagerUtils) {
		List<StatisticsVO> statisticsList = new ArrayList<StatisticsVO>();
		int total = documentBiz.findDocumentFinishedTaskCount(queryConditionVo, true); //已发布 公文数
		int expired = documentBiz.findDocumentFinishedTaskCount(queryConditionVo, false); //已过期 公文数
		
		//如果PagerUtils对象为null，查询所有数据为导出统计功能准备数据，否则执行分页查询
		List<NbcUcTeacher> users;
		if (pagerUtils != null) {
			users = baseClient.queryTeacherList(4, 0, null, (pagerUtils.getPageIndex() - 1) * pagerUtils.getPageSize(), pagerUtils.getPageSize());
			int totalResult = baseClient.queryTeacherCounts(2, "");
			int totalPage = (totalResult % pagerUtils.getPageSize() == 0) ? (totalResult / pagerUtils
					.getPageSize()) : (totalResult / pagerUtils.getPageSize()) + 1;
			int[] pageNumbers = new int[totalPage];
			for (int i = 0; i < totalPage; i++) {
				pageNumbers[i] = (i + 1);
			}
			pagerUtils.setPageNumbers(pageNumbers);
		} else {
			users = baseClient.queryTeacherList(4, 0, null, null, null);
		}
		
		for (NbcUcTeacher user : users) {
			StatisticsVO statVo = new StatisticsVO();
			String handlerId = user.getPid();
			int handledCount = findDocumentTaskHandledCount2(queryConditionVo, handlerId, true); //原发、已处理公文任务
			int unhandledCount = findDocumentTaskHandledCount2(queryConditionVo, handlerId, false); // 原发、已处理、已过期公文任务   TODO:查询语句bug
			statVo.setUserName(user.getName());
			//由于数据问题采用的临时代码，发布时去掉
			String uid = baseClient.queryUidPid(2, handlerId);
			NbcUcDepartment dep = baseClient.queryDepartment(2, uid);
			if (dep == null) {
				statVo.setDepartment("其他");
			} else {
				statVo.setDepartment(dep.getName());
			}
			
			statVo.setHandledCount(handledCount);
			if (total != 0) {
				statVo.setHandlePercent(calPercent(handledCount, total));
			} else {
				statVo.setHandlePercent("0.00%");
			}
			if (expired != 0) {
				statVo.setUnhandledPercent(calPercent(unhandledCount, expired));
			} else {
				statVo.setUnhandledPercent("0.00%");
			}
			statVo.setUnhandledCount(unhandledCount);
			
			statisticsList.add(statVo);
		}
		
		return statisticsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDocumentTaskForwardsUsers(String documentId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> users = (List<String>) getHibernateDao().retrieve(
				"SELECT dt.handlerId FROM DocumentTask dt WHERE dt.documentId = ? AND dt.type = '1'", 
				new Object[]{documentId});
		
		Iterator it = users.iterator();
		while (it.hasNext()) {
			userIdList.add(it.next().toString());
		}
		
		return userIdList;
	}
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#findDocumentTaskUnhandledUsers(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDocumentTaskUnhandledUsers(String documentId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> users = (List<String>) getHibernateDao().retrieve(
				"SELECT dt.handlerId FROM DocumentTask dt WHERE dt.documentId = ? AND dt.isHandled = ? AND dt.type = '0'", 
				new Object[]{documentId, false});
		
		Iterator it = users.iterator();
		while (it.hasNext()) {
			userIdList.add(it.next().toString());
		}
		
		return userIdList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDocumentTaskhandledUsersForwards(String documentId) {
		List<String> userIdList = new ArrayList<String>();
		List<String> users = (List<String>) getHibernateDao().retrieve(
				"SELECT dt.handlerId FROM DocumentTask dt WHERE dt.documentId = ? AND dt.isHandled = ? AND dt.type = '1'", 
				new Object[]{documentId, true});
		
		Iterator it = users.iterator();
		while (it.hasNext()) {
			userIdList.add(it.next().toString());
		}
		
		return userIdList;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#modifyDocumentTaskStatus(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyDocumentTaskStatus(String documentId, String handlerId) {
		DocumentTask documentTask = (DocumentTask) getHibernateDao().get("FROM DocumentTask dt " 
				+ "WHERE dt.documentId = ? AND dt.handlerId = ?", new Object[]{documentId, handlerId});
		documentTask.setIsHandled(true);
		documentTask.setHandlingTime(new Date());
		
		getHibernateDao().update(documentTask);
		
		String uid = baseClient.queryUidPid(2, handlerId);
		try {
			//门户消息推送
			if(PortalMessageUtil.isPortalOK){
				PortalMessageUtil.delPortalPush(documentId, uid);
			}
			//协同办公消息推送
			if(PortalMessageUtil.isIMOK){
				DocumentVO documentVo = documentBiz.findDocument(documentId);
				String receiverId = documentVo.getAuthorId();
				NbcUcTeacher teacher = baseClient.queryTeacher(1, uid);
				String receiverUID = baseClient.queryUidPid(2, receiverId);
				
				OAMessageUtil oAMessageUtil = SupportManager.getOAMessageUtil();
				oAMessageUtil.sendOAMessage(uid, receiverUID, "公文【" + documentVo.getTitle() + "】，" + teacher.getName() + " 已阅。");
				
				System.out.println("协同发送人：" + uid + ", 接收人：" + receiverUID);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentTaskBiz#removeDocumentTasks(java.lang.String)
	 */
	@Override
	public int removeDocumentTasks(String documentId) {
		return getHibernateDao().delete("DELETE DocumentTask dt WHERE dt.documentId = ?", 
				new Object[]{documentId});
	}

	/** 
	 * 根据指定条件返回指定处理人所处理的公文数量
	 * 
	 * @param queryConditionVo 查询条件
	 * @param handlerId 指定的处理人唯一标识
	 * @return 指定处理人所处理的公文数量
	 */ 
	@SuppressWarnings("unchecked")
	private int findDocumentTaskHandledCount(QueryConditionVO queryConditionVo, String handlerId) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentTaskHandledCountQuery(queryConditionVo, handlerId);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(), 
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	private int findDocumentTaskHandledCount2(QueryConditionVO queryConditionVo, String handlerId, boolean bool) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentTaskHandledCountQuery2(queryConditionVo, handlerId, bool);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(), 
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/** 
	 * 根据指定条件返回指定处理人过期未处理的公文数量
	 * 
	 * @param queryConditionVo 查询条件
	 * @param handlerId 指定的处理人唯一标识
	 * @return 指定处理人过期未处理的公文数量
	 */ 
	@SuppressWarnings("unchecked")
	private int findDocumentTaskExpiredCount(QueryConditionVO queryConditionVo, String handlerId) {
		int count = 0;
		try {
			Map<String, Object> queryMap = generateDocumentTaskExpiredCountQuery(queryConditionVo, handlerId);
			count = ((Long) getHibernateDao().get(queryMap.get("queryStr").toString(), 
					((List<Object>) queryMap.get("params")).toArray())).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/** 
	 * 返回按指定条件查询已处理公文数量的查询字符串和参数的Map
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @param handlerId 处理人的唯一标识
	 * @return 按指定条件查询已处理公文数量的查询字符串和参数的Map
	 * @throws ParseException 如果参数中字符串转换时间出错
	 */ 
	private Map<String, Object> generateDocumentTaskHandledCountQuery(QueryConditionVO queryConditionVo,
			String handlerId) throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();

		query.append("SELECT COUNT(*) FROM DocumentTask dt WHERE dt.handlerId = ? " 
				+ "AND dt.isHandled = ? AND dt.type = '0' AND dt.documentId IN (SELECT d.id FROM Document d");
		params.add(handlerId);
		params.add(true);

		if (!StringUtil.isBlank(queryConditionVo.getStarting()) || !StringUtil.isBlank(queryConditionVo.getEnding())) {
			query.append(" WHERE");

			if (!StringUtil.isBlank(queryConditionVo.getStarting()) && StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime >= ?");
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
			} else if (StringUtil.isBlank(queryConditionVo.getStarting()) && !StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime <= ?");
				DateTime ending = new DateTime(queryConditionVo.getEnding());
				params.add(StringUtil.convertStringToDate(ending.plusDays(1).toString()));
			} else if (!StringUtil.isBlank(queryConditionVo.getStarting()) && !StringUtil.isBlank(queryConditionVo.getEnding())) {
				query.append(" d.publishTime >= ? AND d.publishTime <= ?");
				DateTime ending = new DateTime(queryConditionVo.getEnding());
				params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
				params.add(StringUtil.convertStringToDate(ending.plusDays(1).toString()));
			}
		}
		query.append(")");

		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);

		return queryMap;
	}
	
	private Map<String, Object> generateDocumentTaskHandledCountQuery2(QueryConditionVO queryConditionVo,
			String handlerId, boolean bool) throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		query.append("SELECT COUNT(distinct dt.id ) FROM DocumentTask dt, Document d ");
		
		query.append(" WHERE dt.documentId=d.id and dt.isHandled=? and dt.handlerId = ? ");
		params.add(bool);
		params.add(handlerId);
		
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
	 * 返回按指定条件查询已过期公文数量的查询字符串和参数的Map
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @param handlerId 处理人的唯一标识
	 * @return 按指定条件查询已过期公文数量的查询字符串和参数的Map
	 * @throws ParseException 如果参数中字符串转换时间出错
	 */ 
	private Map<String, Object> generateDocumentTaskExpiredCountQuery(QueryConditionVO queryConditionVo,
			String handlerId) throws ParseException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();

		query.append("SELECT COUNT(*) FROM DocumentTask dt WHERE dt.handlerId = ? " 
				+ "AND dt.isHandled = ? AND dt.type = '0' AND dt.documentId IN (SELECT d.id FROM Document d WHERE d.expireTime > ?");
		params.add(handlerId);
		params.add(true);
		params.add(new Date());

		if (!StringUtil.isBlank(queryConditionVo.getStarting())) {
			query.append(" AND d.publishTime >= ?");
			params.add(StringUtil.convertStringToDate(queryConditionVo.getStarting()));
		}
		if (!StringUtil.isBlank(queryConditionVo.getEnding())) {
			query.append(" AND d.publishTime <= ?");
			DateTime ending = new DateTime(queryConditionVo.getEnding());
			params.add(StringUtil.convertStringToDate(ending.plusDays(1).toString()));
		}
		query.append(")");

		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);

		return queryMap;
	}
	
	/** 
	 * 计算两个整数的商，四舍五入保留2位小数
	 * 
	 * @param d1 被除数
	 * @param d2 除数
	 * @return 运算结果
	 */ 
	private String calPercent(int d1, int d2) {
		double result = (double) d1 / d2;
		NumberFormat nf  =  NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		BigDecimal bd = new BigDecimal(result); 
		bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP); 
		return nf.format(bd.doubleValue());
	}

	/** 
	 * 生成已处理公文的查询条件
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @param handlerId 处理人唯一标识
	 * @param status 公文任务的处理状态
	 * @return 已处理公文的查询条件
	 */ 
	private Map<String, Object> generateHandledQuery(QueryConditionVO queryConditionVo, String handlerId, boolean status) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
			if(status){ //已处理
				query.append("SELECT distinct d.id, d.title, d.publishTime, d.authorId, d.status," 
						+ " dt.handlingTime, d.expireTime, dt.type, d.documentSourceId FROM Document d, DocumentTask dt WHERE " 
						+ "dt.isHandled = ? AND dt.handlerId = ? AND d.status in (1,2,3) AND d.id = dt.documentId"); 
				params.add(status);
				params.add(handlerId);
				//params.add(Integer.parseInt("1")); //TODO: 已处理公文状态 改成in (1,2,3)
			}else{ //未处理
				query.append("SELECT distinct d.id, d.title, d.publishTime, d.authorId, d.status," 
						+ " dt.handlingTime, d.expireTime, dt.type, d.documentSourceId FROM Document d, DocumentTask dt WHERE " 
						+ "dt.isHandled = ? AND dt.handlerId = ? AND d.status in (1,3) AND d.id = dt.documentId"); 
				params.add(status);
				params.add(handlerId);
				//params.add(Integer.parseInt("1")); //TODO: 已处理公文状态 改成 in (1,2,3)
			}
		
		if (!StringUtil.isBlank(queryConditionVo.getDocumentName())) {
			query.append(" AND d.title LIKE ?");
			params.add("%" + queryConditionVo.getDocumentName() + "%");
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
		if(status){ //已处理
			query.append(" ORDER BY dt.handlingTime DESC ");
		}else{ //未处理
			query.append(" ORDER BY d.status, d.publishTime DESC");
		}
		
		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);
		
		return queryMap;
	}
	/** 
	 * 生成已处理公文数量的查询条件
	 * 
	 * @param queryConditionVo 查询条件的值对象
	 * @param handlerId 处理人唯一标识
	 * @param status 公文任务的处理状态
	 * @return 已处理公文的查询条件
	 */ 
	private Map<String, Object> generateHandledCount(QueryConditionVO queryConditionVo, String handlerId, boolean status) {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		query.append("SELECT d.id, d.title, d.publishTime, d.authorId, d.status," 
				+ " dt.handlingTime, d.expireTime, dt.type FROM Document d, DocumentTask dt WHERE " 
				+ "dt.isHandled = ? AND dt.handlerId = ? AND d.status = ? AND d.id = dt.documentId");
		params.add(status);
		params.add(handlerId);
		params.add(Integer.parseInt("1"));
		
		if (!StringUtil.isBlank(queryConditionVo.getDocumentName())) {
			query.append(" AND d.title LIKE ?");
			params.add("%" + queryConditionVo.getDocumentName() + "%");
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
		query.append(" ORDER BY d.status, d.publishTime");
		
		queryMap.put("queryStr", query.toString());
		queryMap.put("params", params);
		
		return queryMap;
	}
	
	public void setDocumentBiz(DocumentBiz documentBiz) {
		this.documentBiz = documentBiz;
	}

	public BaseClient getBaseClient() {
		return baseClient;
	}
	
	public void setBaseClient(BaseClient baseClient) {
		this.baseClient = baseClient;
	}

	public DocumentSourceBiz getDocumentSourceBiz() {
		return documentSourceBiz;
	}

	public void setDocumentSourceBiz(DocumentSourceBiz documentSourceBiz) {
		this.documentSourceBiz = documentSourceBiz;
	}
}
