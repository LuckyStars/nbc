package com.nbcedu.function.documentflow.biz.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz;
import com.nbcedu.function.documentflow.biz.NotifyTimeBiz;
import com.nbcedu.function.documentflow.model.NotifyTime;
import com.nbcedu.function.documentflow.vo.NotifyTimeVO;

public class NotifyTimeBizImplTests {
	
	private static ApplicationContext ctx;
	private NotifyTimeBiz notifyTimeBiz;
	private NotifyTime notifyTime;
	private NotifyTimeVO notifyTimeVo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/bizContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/daoContext.xml",
				"com/nbcedu/core/privilege/config/spring-conf/daoContext.xml",
				"com/nbcedu/core/privilege/config/spring-conf/bizContext.xml"});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		notifyTimeBiz = (NotifyTimeBiz) ctx.getBean("notifyTimeBiz");
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("IMMEDIATE");
		notifyTimeVo.setDisplayName("发布时");
		notifyTimeVo.setOrderId(1);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("AFTER1");
		notifyTimeVo.setDisplayName("发布后1小时");
		notifyTimeVo.setOrderId(2);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("AFTER24");
		notifyTimeVo.setDisplayName("发布后24小时");
		notifyTimeVo.setOrderId(3);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("AFTER72");
		notifyTimeVo.setDisplayName("发布后72小时");
		notifyTimeVo.setOrderId(4);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("LAST48");
		notifyTimeVo.setDisplayName("过期前48小时");
		notifyTimeVo.setOrderId(5);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
		
		notifyTimeVo = new NotifyTimeVO();
		notifyTime = new NotifyTime();
		
		notifyTimeVo.setName("LAST24");
		notifyTimeVo.setDisplayName("过期前24小时");
		notifyTimeVo.setOrderId(6);
		
		notifyTimeBiz.add(notifyTimeVo, notifyTime);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindNotifyTimes() {
		assertEquals(6, notifyTimeBiz.findNotifyTimes().size());
		
		assertEquals(3, notifyTimeBiz.findNotifyTimes().get(2).getOrderId());
	}

	@Test
	public void testFindNotifyTimesSerializable() {
		assertEquals(3, notifyTimeBiz.findNotifyTimes("85c6fe45c23911e0b43b0025645a252a").size());
		
		assertEquals(5, notifyTimeBiz.findNotifyTimes("85c6fe45c23911e0b43b0025645a252a").get(1).getOrderId());
	}

}
