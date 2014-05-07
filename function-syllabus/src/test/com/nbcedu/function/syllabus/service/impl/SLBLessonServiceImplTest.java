package com.nbcedu.function.syllabus.service.impl;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.service.SLBLessonService;

public class SLBLessonServiceImplTest  {
	
	private static ApplicationContext ctx;
	private SLBLessonService sLBLessonService;
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
		sLBLessonService  = (SLBLessonService) ctx.getBean("slbLessonService");
	}

	@After
	public void tearDown() throws Exception {
	}


//	@Test
//	public void testFindById() {
//		SLBLesson s = sLBLessonService.get("1");
//		System.out.println(s.getId());
//		System.out.println(s.getsLBCourse().getName());
//	}

	@Test
	public void testFindBy() {
		List<SLBLesson> list = (List<SLBLesson>)sLBLessonService.findBy("classId","1");  
		System.out.println(list.size());
	}


}
