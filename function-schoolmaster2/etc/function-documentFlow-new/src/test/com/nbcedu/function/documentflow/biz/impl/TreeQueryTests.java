package com.nbcedu.function.documentflow.biz.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.documentflow.biz.TreeNodeBiz;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcPerson;
import com.nbcedu.integration.uc.client.vo.NbcUcTeacher;

public class TreeQueryTests {
	
	private static ApplicationContext ctx;
	private TreeNodeBiz treeNodeBiz;

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
		treeNodeBiz = (TreeNodeBiz) ctx.getBean("treeNodeBiz");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTreeNodeQuery() {
		//List<Map<String, Object>> nodeJsonList = treeNodeBiz.findAllNodes();
		//System.out.println(JSONArray.toJSONString(nodeJsonList));
	}
	
	@Test
	public void testUID() {
		BaseClient client = new BaseClient();
//		NbcUcTeacher user = client.queryTeacher(1, "006091eaa14c11e0abc0b0487a06bd71");
//		System.out.println(client.queryDepartment(2, client.queryUidPid(2, user.getPid())).getName());
		System.out.println(client.queryTeacherCounts(2, null));
	}
}
