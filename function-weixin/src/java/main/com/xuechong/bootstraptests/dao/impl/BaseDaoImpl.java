package com.xuechong.bootstraptests.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuechong.bootstraptests.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>{
	
	protected final int PAGE_SIZE = 6;
	// 当前泛型实际实体类的CLASS对象
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired(required=true)
	protected SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void add(T t) {
		getSession().save(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(Integer pageNo) {
		Criteria cri = this.getSession().createCriteria(persistentClass);
		cri.setFirstResult(pageNo==null?0:(pageNo-1)*PAGE_SIZE);
		cri.setMaxResults(PAGE_SIZE);
		cri.addOrder(Order.desc("createDate"));
		return cri.list();
	}

	@Override
	public void remove(String id) {
		this.getSession().delete("id", id);
	}

	@Override
	public Integer totalPage() {
		Criteria cri = this.getSession().createCriteria(persistentClass);
		cri.setProjection(Projections.rowCount());
		Integer totalCount = Integer.parseInt(cri.uniqueResult().toString());
		return totalCount%PAGE_SIZE==0?totalCount/PAGE_SIZE:(totalCount/PAGE_SIZE)+1;
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listByProp(String prop, Object value, Integer pageNo) {
		Criteria cri = this.getSession().createCriteria(persistentClass);
		cri.add(Restrictions.eq(prop, value));
		cri.setFirstResult(pageNo==null?0:(pageNo-1)*PAGE_SIZE);
		cri.setMaxResults(PAGE_SIZE);
		cri.addOrder(Order.desc("createDate"));
		return cri.list();
	}

	@Override
	public Integer totalPageByProp(String prop, Object value) {
		Criteria cri = this.getSession().createCriteria(persistentClass);
		cri.add(Restrictions.eq(prop, value));
		cri.setProjection(Projections.rowCount());
		Integer totalCount = Integer.parseInt(cri.uniqueResult().toString());
		return totalCount%PAGE_SIZE==0?totalCount/PAGE_SIZE:(totalCount/PAGE_SIZE)+1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(String id) {
		Criteria cri = this.getSession().createCriteria(persistentClass);
		cri.add(Restrictions.eq("id", id));
		return (T) cri.uniqueResult();
	}

}
