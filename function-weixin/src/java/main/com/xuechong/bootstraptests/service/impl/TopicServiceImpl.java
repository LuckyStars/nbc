package com.xuechong.bootstraptests.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuechong.bootstraptests.dao.TopicDao;
import com.xuechong.bootstraptests.model.Comment;
import com.xuechong.bootstraptests.model.Topic;
import com.xuechong.bootstraptests.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl implements TopicService{

	@Resource(name="topicDao")
	private TopicDao topicDao;
	
	@Override
	public void add(Topic t) {
		this.topicDao.add(t);
	}

	@Override
	public List<Topic> list(Integer page) {
		return this.topicDao.list(page);
	}

	@Override
	public void remove(String id) {
		this.topicDao.remove(id);
	}

	@Override
	public int totalPage() {
		return this.topicDao.totalPage();
	}

	@Override
	public Topic findById(String id) {
		return this.topicDao.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findTop(int top) {
		top = top>0?top:5;
		List<Topic> result =null;
		Session session = this.topicDao.getSession();
		Collection<String> ids = new ArrayList<String>(top);
		Query query = session.createQuery("SELECT c.topicId,count(c.topicId) FROM Comment c GROUP BY c.topicId ORDER BY count(c.topicId) DESC,c.createDate ASC ");
		query.setFirstResult(0);
		query.setMaxResults(top);
		List<Object[]> idlist = query.list();
		for (Object[] objects : idlist) {
			ids.add((String) objects[0]);
		}
		Criteria cri = session.createCriteria(Topic.class);
		cri.add(Restrictions.in("id",ids));
		result = cri.list();
		
		for (Object[] objects : idlist) {
			for (Topic topic : result) {
				if(topic.getId().equals(objects[0])){
					topic.setCommentCount(Integer.valueOf(objects[1].toString()));
				}
			}
		}
		Collections.sort(result,new Comparator<Topic>() {
			@Override
			public int compare(Topic o1, Topic o2) {
				return o1.getCommentCount()<o2.getCommentCount()?1:-1;
			}
		});
		return result;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
		
	}

}
