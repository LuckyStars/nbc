package com.nbcedu.function.documentflow.biz.impl;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

public class UcClientTests {
	
	private BaseClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client = new BaseClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryTeacherList() {
		List<NbcUcTeacher> users = client.queryTeacherList(4, 0, null, null, null);
		assertEquals(294, users.size());
	}
}
