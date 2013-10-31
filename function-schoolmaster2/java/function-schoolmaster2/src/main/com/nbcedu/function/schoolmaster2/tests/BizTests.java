package com.nbcedu.function.schoolmaster2.tests;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;

public class BizTests {
	
	static ApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext(new String[]{
				"applicationContext.xml",
				"com/nbcedu/function/schoolmaster2/config/spring-conf/actionContext.xml",
				"com/nbcedu/function/schoolmaster2/config/spring-conf/bizContext.xml",
				"com/nbcedu/function/schoolmaster2/config/spring-conf/daoContext.xml"});
	}
	@Test
	public void masterSubBizTest(){
		SM2MasterSubBiz biz = (SM2MasterSubBiz) context.getBean("masterSubBiz");
		biz.findByMaster("guanzhu", "1");
	}
	
	
}
