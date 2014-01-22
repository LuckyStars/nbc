package com.xuechong.bootstraptests.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao <T>{
	public void add(T t);
	public List<T> list(Integer pageNo);
	public void remove(String id);
	public Integer totalPage();
	public void update(T t);
	public List<T> listByProp(String prop,Object value,Integer page);
	public Integer totalPageByProp(String prop,Object vlue);
	public T findById(String id);
	Session getSession();
}
