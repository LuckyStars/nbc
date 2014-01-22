package com.xuechong.bootstraptests.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuechong.bootstraptests.model.Topic;

@Service
@Transactional
public interface TopicService {
	public void add(Topic t);
	public List<Topic> list(Integer page);
	public int totalPage();
	public void remove(String id);
	public Topic findById(String id);
	public List<Topic> findTop(int top);
	public void setSessionFactory(SessionFactory sessionFactory);
}
