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

import com.nbcedu.function.documentflow.biz.ExporterBiz;
import com.nbcedu.function.documentflow.vo.StatisticsVO;

public class ExporterBizImplTests {
	
	private static ApplicationContext ctx;
	private ExporterBiz exporterBiz;
	private List<StatisticsVO> statlist;

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
		exporterBiz = (ExporterBiz) ctx.getBean("exporterBiz");
		
		statlist = new ArrayList<StatisticsVO>();
		
		StatisticsVO s1 = new StatisticsVO();
		StatisticsVO s2 = new StatisticsVO();
		StatisticsVO s3 = new StatisticsVO();
		
		s1.setUserName("王卓譞");
		s1.setDepartment("产品研发部");
		s1.setHandledCount(15);
		//s1.setHandlePercent(15.93);
		s1.setUnhandledCount(2);
		//s1.setUnhandledPercent(0.09);
		
		s2.setUserName("小刚");
		s2.setDepartment("营销部");
		s2.setHandledCount(12);
		//s2.setHandlePercent(12.29);
		s2.setUnhandledCount(1);
		//s2.setUnhandledPercent(0.08);
		
		s3.setUserName("小娟");
		s3.setDepartment("营销部");
		
		statlist.add(s1);
		statlist.add(s2);
		statlist.add(s3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExport() {
//		exporterBiz.export(statlist, "王卓譞");
	}

}
