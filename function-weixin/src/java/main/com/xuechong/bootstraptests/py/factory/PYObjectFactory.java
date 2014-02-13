package com.xuechong.bootstraptests.py.factory;


import org.apache.log4j.Logger;

import com.xuechong.bootstraptests.service.TopicService;

import static com.xuechong.bootstraptests.py.factory.PyFactoryUtil.*;

public class PYObjectFactory {

	private static final Logger logger = Logger.getLogger(PYObjectFactory.class);
	
	public static TopicService getTopicService(){
		TopicService service = (TopicService) getByClass(TopicService.class);
		return service;
	}
	
}
