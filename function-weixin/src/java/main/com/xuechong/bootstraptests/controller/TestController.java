package com.xuechong.bootstraptests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.xuechong.bootstraptests.model.TestModel;
import com.xuechong.bootstraptests.service.TestService;

@SuppressWarnings("deprecation")
@Controller("testController")
@RequestMapping("/test")
public class TestController extends SimpleFormController{
	
	@Autowired(required=true)
	private TestService service;
	
	@RequestMapping("/execute/{value}")
	public String execute(@PathVariable("value")String value,ModelMap modelMap){
		System.out.println("Method execute:" + value);
		modelMap.put("value", value);
		service.addSomeDBtrans();
		return "pages/index.jsp";
	}
	
	@RequestMapping("/model/exe")
	public String modelExe(TestModel model,ModelMap modelMap){
		System.out.println(model.getName());
		return "pages/index.jsp";
	}
	
	public void setService(TestService service) {
		this.service = service;
	}
	
	
}
