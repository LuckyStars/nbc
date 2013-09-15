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

import com.nbcedu.core.privilege.biz.RoleBiz;
import com.nbcedu.core.privilege.biz.UserBiz;
import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;

public class RoleBizImplTests {
	
	private static ApplicationContext ctx;
	private RoleBiz roleBiz;
	private UserBiz userBiz;
	private Role role;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", 
				"com/nbcedu/core/privilege/config/spring-conf/daoContext.xml", 
				"com/nbcedu/core/privilege/config/spring-conf/bizContext.xml"});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		roleBiz = (RoleBiz) ctx.getBean("roleBiz");
		userBiz = (UserBiz) ctx.getBean("userBiz");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testUser() {
		System.out.println(userBiz.findUsersByRoleName("DOCUMENTFLOW_TRANSFERER").size());
//		User user = userBiz.findUserByPid("06974f65d53411e0995a00237ddf16b2");
//		Role publisher = roleBiz.findRoleByName("DOCUMENTFLOW_PUBLISHER");
//		System.out.println(user.getName());
//		List<User> users = publisher.getUsers();
//		System.out.println(publisher.getId());
	}
	
	@Test
	public void testAddRole() {
		Role role1 = new Role();
		role1.setName("DOCUMENTFLOW_COMMON");
		role1.setDisplayName("普通用户");
		
		Role role2 = new Role();
		role2.setName("DOCUMENTFLOW_ADMIN");
		role2.setDisplayName("公文管理员");
		
		Role role3 = new Role();
		role3.setName("DOCUMENTFLOW_PUBLISHER");
		role3.setDisplayName("公文发布员");
		
		Role role4 = new Role();
		role4.setName("DOCUMENTFLOW_TRANSFERER");
		role4.setDisplayName("公文转发员");
		
		roleBiz.addRole(role1);
		roleBiz.addRole(role2);
		roleBiz.addRole(role3);
		roleBiz.addRole(role4);
		
		assertNotNull(role1.getId());
		assertNotNull(role2.getId());
		assertNotNull(role3.getId());
		assertNotNull(role4.getId());
	}

}
