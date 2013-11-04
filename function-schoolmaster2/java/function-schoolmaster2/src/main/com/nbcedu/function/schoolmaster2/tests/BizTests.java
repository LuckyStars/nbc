package com.nbcedu.function.schoolmaster2.tests;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.data.vo.ProgressVo;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

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
	
	
	@Test
	public void progTest(){
		Sm2ProgressBiz biz = (Sm2ProgressBiz)context.getBean("sm2ProgressBiz");
		List<ProgressVo>result = biz.findVoByStepId("2");
		for (ProgressVo progressVo : result) {
			System.out.println(progressVo.getContent());
		}
		System.out.println(result.toString());
	}
	
	@Test
	public void subjectTest(){
		SM2SubjectBiz biz = (SM2SubjectBiz)context.getBean("sm2SubjectBiz");
		SubjectVo s = new SubjectVo();
		s.setExcuteUserId("J201108231101060006569");
		s.setModuleId("lssx");
		List<TSm2Subject> result = biz.findByExceuteUserId(s).getDatas();
	}
	
}
