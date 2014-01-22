package com.xuechong.bootstraptests.dao.impl;



import org.springframework.stereotype.Repository;

import com.xuechong.bootstraptests.dao.TopicDao;
import com.xuechong.bootstraptests.model.Topic;
@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao{

	
}
