/*
 * @Title: SchedulerBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: SchedulerBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-21 下午10:38:19
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-21                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.JobDetailBean;

import com.nbcedu.function.documentflow.biz.SchedulerBiz;

/** 
 * <p>SchedulerBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-21 下午10:38:19
 */
public class SchedulerBizImpl implements SchedulerBiz {
	
	/**
	 * 日志对象
	 */
	private static final Logger logger = Logger.getLogger(SchedulerBizImpl.class);
	/**
	 * 定时调度接口
	 */
	private Scheduler scheduler;
	/**
	 * 定时任务Map
	 */
	private Map<String, JobDetailBean> jobDetailMap;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String)
	 */
	@Override
	public void schedule(String jobDetailName, String cronExpression) {
		schedule(jobDetailName, null, cronExpression);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void schedule(String jobDetailName, String name, String cronExpression) {
		try {
			schedule(jobDetailName, name, new CronExpression(cronExpression));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, org.quartz.CronExpression)
	 */
	@Override
	public void schedule(String jobDetailName, CronExpression cronExpression) {
		schedule(jobDetailName, null, cronExpression);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, org.quartz.CronExpression)
	 */
	@Override
	public void schedule(String jobDetailName, String name, CronExpression cronExpression) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}

		JobDetail jobDetail = jobDetailMap.get(jobDetailName);
		
		try {
			scheduler.addJob(jobDetail, true);

			CronTrigger cronTrigger = new CronTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail.getName(),
					Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(cronExpression);
			scheduler.scheduleJob(cronTrigger);
			scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, cronTrigger);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.util.Date)
	 */
	@Override
	public void schedule(String jobDetailName, Date startTime) {
		schedule(jobDetailName, startTime, null);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public void schedule(String jobDetailName, String name, Date startTime) {
		schedule(jobDetailName, name, startTime, null);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public void schedule(String jobDetailName, Date startTime, Date endTime) {
		schedule(jobDetailName, startTime, endTime, 0);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public void schedule(String jobDetailName, String name, Date startTime, Date endTime) {
		schedule(jobDetailName, name, startTime, endTime, 0);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.util.Date, java.util.Date, int)
	 */
	@Override
	public void schedule(String jobDetailName, Date startTime, Date endTime, int repeatCount) {
		schedule(jobDetailName, null, startTime, endTime, 0);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, java.util.Date, java.util.Date, int)
	 */
	@Override
	public void schedule(String jobDetailName, String name, Date startTime, Date endTime, int repeatCount) {
		schedule(jobDetailName, name, startTime, endTime, 0, 0L);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.util.Date, java.util.Date, int, long)
	 */
	@Override
	public void schedule(String jobDetailName, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(jobDetailName, null, startTime, endTime, repeatCount, repeatInterval);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.lang.String, java.util.Date, java.util.Date, int, long)
	 */
	@Override
	public void schedule(String jobDetailName, String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}

		JobDetail jobDetail = jobDetailMap.get(jobDetailName);
		
		try {
			scheduler.addJob(jobDetail, true);

			SimpleTrigger simpleTrigger = new SimpleTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail
					.getName(), Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval);
			scheduler.scheduleJob(simpleTrigger);
			scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, simpleTrigger);

		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.SchedulerBiz#schedule(java.lang.String, java.util.Map, java.util.Date)
	 */
	@Override
	public void schedule(String jobDetailName, Map<String, String> paramMap, Date startTime) {
//		String name = UUID.randomUUID().toString();
		JobDetail jobDetail = jobDetailMap.get(jobDetailName);
		
		String docId = (String) paramMap.get("docId");
		jobDetail.getJobDataMap().put("docId", docId);
		jobDetail.setName(jobDetailName + "_" + docId);
		
		String name = jobDetailName + "_" + docId;
		
		//根据调用的任务不同初始化不同的参数到上下文中
		if (jobDetailName.equals("notifyingJobBean")) {
			String content = (String) paramMap.get("content");
			jobDetail.getJobDataMap().put("content", content);
		}
		
		try {
			SimpleTrigger simpleTrigger = (SimpleTrigger) scheduler.getTrigger(name, Scheduler.DEFAULT_GROUP);
			if (simpleTrigger == null) {
				simpleTrigger = new SimpleTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail
						.getName(), Scheduler.DEFAULT_GROUP, startTime, null, 0, 0L);
			} else {
				//如果触发器已存在，则删除关联的计划任务，重新设置开始时间
				scheduler.deleteJob(jobDetail.getName(), Scheduler.DEFAULT_GROUP);
				simpleTrigger.setStartTime(startTime);
			}
			scheduler.addJob(jobDetail, true);
			scheduler.scheduleJob(simpleTrigger);
			scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, simpleTrigger);

		} catch (SchedulerException e) {
			logger.info("添加计划任务异常，异常的公文标识：" + docId);
			logger.info(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * @param jobDetailMap the jobDeatailMap to set
	 */
	public void setJobDetailMap(Map<String, JobDetailBean> jobDetailMap) {
		this.jobDetailMap = jobDetailMap;
	}
}
