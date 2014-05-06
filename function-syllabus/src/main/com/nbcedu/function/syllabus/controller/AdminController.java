package com.nbcedu.function.syllabus.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/syllabus/admin")
@Scope("prototype")
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);
}
