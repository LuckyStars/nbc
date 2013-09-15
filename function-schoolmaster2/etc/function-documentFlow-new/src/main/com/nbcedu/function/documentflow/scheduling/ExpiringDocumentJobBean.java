/*
 * @Title: ExpiringDocumentJobBean.java
 * @Package com.nbcedu.function.documentflow.scheduling
 * @Description: 公文过期的定时任务对象。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-22 上午08:59:25
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-22                          
 */
package com.nbcedu.function.documentflow.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nbcedu.function.documentflow.biz.DocumentBiz;
import com.nbcedu.function.documentflow.biz.impl.DocumentBizImpl;
import com.nbcedu.function.documentflow.service.SpringBeanService;
import com.nbcedu.function.documentflow.utils.PortalMessageUtil;

/** 
 * <p>公文过期的定时任务对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-22 上午08:59:25
 */
public class ExpiringDocumentJobBean extends QuartzJobBean {
	/**
	 * SpringBeanService对象
	 */
	private SpringBeanService springBeanService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		DocumentBiz documentBiz = springBeanService.getBean(DocumentBizImpl.class, "documentBiz");
		String docId = (String) arg0.getJobDetail().getJobDataMap().get("docId");
		String status = documentBiz.findDocument(docId).getStatus();
		if ("流转中".equals(status)) {
			documentBiz.modifyDocumentStatus(docId, 3);
			
			//删除门户消息
			PortalMessageUtil.delPortalPushByDocId(docId);
		}
	}

	public void setSpringBeanService(SpringBeanService springBeanService) {
		this.springBeanService = springBeanService;
	}
}
