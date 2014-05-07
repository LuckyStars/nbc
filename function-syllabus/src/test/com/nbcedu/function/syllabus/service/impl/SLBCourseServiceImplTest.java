package com.nbcedu.function.syllabus.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.syllabus.service.SLBUploadService;

public class SLBCourseServiceImplTest  {
	
	private static ApplicationContext ctx;
	private SLBUploadService slbCourseService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", 
				"config/spring/hibernateContext.xml", 
				"config/spring/jdbctemplateContext.xml",
				"config/spring/daoContext.xml"});
//		ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		slbCourseService  = (SLBUploadService) ctx.getBean("slbCourseService");
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testAddDocumentSource() {
//		documentSourceVo = new DocumentSourceVO();
//		documentSourceVo.setDisplayName("工委");
//		
//		assertEquals(0, documentSourceBiz.findDocumentSources().size());
//		
//		documentSourceBiz.addDocumentSource(documentSourceVo);
//		
//		assertEquals(1, documentSourceBiz.findDocumentSources().size());
//	}
//
//	@Test
//	public void testFindById() {
//		boolean b = slbCourseService.delete("1");
//		System.out.println(b);
//	}

	@Test
	public void testFindDocumentSources() {
//		SLBCourse s = slbCourseService.get("1");
//		System.out.println(s.getName());
//		System.out.println(s.getSets().size());
	}


}
