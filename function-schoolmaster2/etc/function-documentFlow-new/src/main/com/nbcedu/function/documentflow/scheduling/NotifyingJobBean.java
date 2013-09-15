/*
 * @Title: NotifyingJobBean.java
 * @Package com.nbcedu.function.documentflow.scheduling
 * @Description: 短信提醒的定时调度对象。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-22 上午11:14:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-22                          
 */
package com.nbcedu.function.documentflow.scheduling;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nbcedu.common.utils.PropertyUtils;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.biz.impl.DocumentTaskBizImpl;
import com.nbcedu.function.documentflow.service.SpringBeanService;
import com.nbcedu.function.documentflow.utils.MessageTester;
import com.nbcedu.function.functionsupport.core.MobileMessageUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.integration.uc.client.facade.BaseClient;

/** 
 * <p>短信提醒的定时调度对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-22 上午11:14:12
 */
public class NotifyingJobBean extends QuartzJobBean {
	
	/**
	 * 日志记录器
	 */
	private static Logger logger = Logger.getLogger(NotifyingJobBean.class);
	/**
	 * SpringBeanService对象
	 */
	private SpringBeanService springBeanService;

	/* 
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		DocumentTaskBiz documentTaskBiz = springBeanService.getBean(DocumentTaskBizImpl.class, "documentTaskBiz");
		List<String> unhandledUserList = documentTaskBiz.findDocumentTaskUnhandledUsers(
				(String) arg0.getJobDetail().getJobDataMap().get("docId"));
		StringBuilder target = new StringBuilder();
		BaseClient client = new BaseClient();
//		String mode = PropertyUtils.readValue("appconfig.properties", "function.documentFlow.mode");
		
		if (unhandledUserList != null && unhandledUserList.size() > 0) {
			for (String pid : unhandledUserList) {
				target.append(client.queryTeacher(1, pid).getTelephone() + ",");
			}
			target.deleteCharAt(target.lastIndexOf(","));
		}
		logger.info("发送的手机号码：" + target.toString() + ",发送的内容：" + (String) arg0.getJobDetail().getJobDataMap().get("content"));
//		if (!"dev".equals(mode)) {
		System.out.println("发送的手机号码：" + target.toString() + ",发送的内容：" + (String) arg0.getJobDetail().getJobDataMap().get("content"));
			MobileMessageUtil mobileMessageUtil = SupportManager.getMobileMessageUtil();
			mobileMessageUtil.sendMobileMessage(target.toString(), (String) arg0.getJobDetail().getJobDataMap().get("content"));
//		}else{
//			MessageTester.sendMessage(target.toString(), (String) arg0.getJobDetail().getJobDataMap().get("content"));
//		}
//		
	}
	
	/**
	 * @param springBeanService the springBeanService to set
	 */
	public void setSpringBeanService(SpringBeanService springBeanService) {
		this.springBeanService = springBeanService;
	}
}
