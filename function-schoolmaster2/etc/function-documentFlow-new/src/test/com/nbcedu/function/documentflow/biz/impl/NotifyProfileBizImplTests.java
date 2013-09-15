package com.nbcedu.function.documentflow.biz.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.documentflow.biz.NotifyProfileBiz;
import com.nbcedu.function.documentflow.biz.NotifyTimeBiz;
import com.nbcedu.function.documentflow.vo.NotifyProfileVO;
import com.nbcedu.function.documentflow.vo.NotifyTimeVO;

public class NotifyProfileBizImplTests {
	
	private static ApplicationContext ctx;
	private NotifyProfileVO notifyProfileVo;
	private List<NotifyTimeVO> notifyTimeVos;
	private NotifyProfileBiz notifyProfileBiz;
	private NotifyTimeBiz notifyTimeBiz;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/bizContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/daoContext.xml",
				"com/nbcedu/core/privilege/config/spring-conf/bizContext.xml",
				"com/nbcedu/core/privilege/config/spring-conf/daoContext.xml"});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		notifyProfileBiz = (NotifyProfileBiz) ctx.getBean("notifyProfileBiz");
		notifyTimeBiz = (NotifyTimeBiz) ctx.getBean("notifyTimeBiz");
		
//		notifyTimeVos = notifyTimeBiz.findNotifyTimes();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNotifyProfile() {
		notifyProfileVo = new NotifyProfileVO();
//		NotifyTimeVO[] notifyTimes = new NotifyTimeVO[]{notifyTimeVos.get(0), notifyTimeVos.get(3)};
		notifyProfileVo.setIsDefault(false);
//		notifyProfileVo.setIsEdit(true);
		notifyProfileVo.setProfileName("紧急");
//		notifyProfileVo.setContent("您的办公平台收到待处理公文，请及时处理。");
//		notifyProfileVo.setNotifyTimeVos(notifyTimes);
		
		notifyProfileBiz.addNotifyProfile(notifyProfileVo);
		
//		assertEquals(1, notifyProfileBiz.findNotifyProfiles().size());
//		assertEquals(2, notifyProfileBiz.findNotifyProfiles().get(0).getNotifyTimeVos().length);
	}

	@Test
	public void testFindNotifyProfiles() {
		assertEquals(0, notifyProfileBiz.findNotifyProfiles().size());
	}

	@Test
	public void testFindNotifyProfilesNotifyProfileVO() {
		notifyProfileVo = notifyProfileBiz.findNotifyProfile("63b3c508c31511e08f5c0025645a252a");
		
		assertEquals(2, notifyProfileVo.getNotifyTimeVos().length);
		assertEquals(1, notifyProfileVo.getNotifyTimeVos()[0].getOrderId());
	}

	@Test
	public void testModifyNotifyProfile() {
		List<NotifyTimeVO> notifyTimeVoList = new ArrayList<NotifyTimeVO>();
		NotifyProfileVO profile = notifyProfileBiz.findNotifyProfile("4c72967fe99d11e08f250025645a252a");
		String[] notifyTimeIdArray = new String[]{"17517ff5e4dc11e0962e0025645a252a", "17936af7e4dc11e0962e0025645a252a"};
		profile.setIsDefault(false);
		profile.setIsEdit(true);
		profile.setProfileName("啊啊啊aaa");
		profile.setContent("aaa");
		
		System.out.println(profile.getId());
		
		for (String notifyTimeId : notifyTimeIdArray) {
			notifyTimeVoList.add(notifyTimeBiz.findNotifyTime(notifyTimeId));
		}
		profile.setNotifyTimeVos(notifyTimeVoList.toArray(new NotifyTimeVO[notifyTimeVoList.size()]));
		
		try {
			notifyProfileBiz.modifyNotifyProfile(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveNotifyProfile() {
		notifyProfileVo = new NotifyProfileVO();
		notifyProfileVo.setId("63b3c508c31511e08f5c0025645a252a");
		
		notifyProfileBiz.removeNotifyProfile(notifyProfileVo);
		
		assertEquals(0, notifyProfileBiz.findNotifyProfiles().size());
	}

}
