package com.nbcedu.function.documentflow.biz.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;

public class DocumentSourceBizImplTests {
	
	private static ApplicationContext ctx;
	private DocumentSourceBiz documentSourceBiz;
	private DocumentSourceVO documentSourceVo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/bizContext.xml", 
				"com/nbcedu/function/documentflow/config/spring-conf/daoContext.xml"});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		documentSourceBiz = (DocumentSourceBiz) ctx.getBean("documentSourceBiz");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setDisplayName("工委");
		
		assertEquals(0, documentSourceBiz.findDocumentSources().size());
		
		documentSourceBiz.addDocumentSource(documentSourceVo);
		
		assertEquals(1, documentSourceBiz.findDocumentSources().size());
	}

	@Test
	public void testFindDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setId("a7edd181bf7e11e09b600025645a252a");
		
		assertNotNull(documentSourceBiz.findDocumentSource(documentSourceVo));
	}

	@Test
	public void testFindDocumentSources() {
		assertEquals(0, documentSourceBiz.findDocumentSources().size());
	}

	@Test
	public void testModifyDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setId("a7edd181bf7e11e09b600025645a252a");
		documentSourceVo.setDisplayName("教委");
		
		documentSourceBiz.modifyDocumentSource(documentSourceVo);
		
		assertEquals("教委", documentSourceBiz.findDocumentSource(documentSourceVo).getDisplayName());
	}

	@Test
	public void testRemoveDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setId("a7edd181bf7e11e09b600025645a252a");
		
		documentSourceBiz.removeDocumentSource(documentSourceVo);
		
		assertEquals(0, documentSourceBiz.findDocumentSources().size());
	}
}
