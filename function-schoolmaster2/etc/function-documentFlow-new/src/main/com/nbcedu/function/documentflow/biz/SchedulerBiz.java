/*
 * @Title: SchedulerBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: 定时任务调度器接口，该接口包含了定时调度的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-21 下午10:17:53
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-21                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.Date;
import java.util.Map;

import org.quartz.CronExpression;

/**
 * <p>
 * 定时任务调度器接口
 * </p>
 * 
 * @author Wang Zhuoxuan Create at:2011-9-21 下午10:17:54
 */
public interface SchedulerBiz {
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String jobDetailName, String cronExpression);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String jobDetailName, String name, String cronExpression);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param cronExpression Quartz CronExpression
	 */
	void schedule(String jobDetailName, CronExpression cronExpression);

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	 */
	void schedule(String jobDetailName, String name, CronExpression cronExpression);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param startTime 调度开始时间
	 */
	void schedule(String jobDetailName, Date startTime);

	/**
	 * 在startTime时执行调试一次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 */
	void schedule(String jobDetailName, String name, Date startTime);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	void schedule(String jobDetailName, Date startTime, Date endTime);

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	void schedule(String jobDetailName, String name, Date startTime, Date endTime);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	void schedule(String jobDetailName, Date startTime, Date endTime, int repeatCount);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	void schedule(String jobDetailName, String name, Date startTime, Date endTime, int repeatCount);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	void schedule(String jobDetailName, Date startTime, Date endTime, int repeatCount, long repeatInterval);

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	void schedule(String jobDetailName, String name, Date startTime, Date endTime, int repeatCount, long repeatInterval);
	
	/** 
	 * 在startTime时执行调试一次
	 * 
	 * @param jobDetailName 要执行的调度任务的Bean名称
	 * @param paramMap 执行调度任务时所需的参数
	 * @param startTime 调度开始时间
	 */ 
	void schedule(String jobDetailName, Map<String, String> paramMap, Date startTime);
}
