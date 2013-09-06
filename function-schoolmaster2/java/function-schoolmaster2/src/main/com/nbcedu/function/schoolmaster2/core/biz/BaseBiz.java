package com.nbcedu.function.schoolmaster2.core.biz;


import java.io.Serializable;
import java.util.List;

public interface BaseBiz<T extends Serializable> {
	
	public boolean getEquesName(Class<T> c,String name);
	
	public T findById(Serializable id) ;
	
	public T load(Serializable id) ;

	public List<T> findAll() ;

	public T add(T entity) ;

	public T addAndRefresh(T entity);

	public Object addOrUpdate(T o);

	public void remove(T o) ;

	public Object modify(T o) ;

	public Object modifyMerge(T o) ;

	public T removeById(Serializable id);
	
	public T removeById(String id);

	public List<T> removeById(Serializable[] ids) ;
	
	public String getIdName() ;
	
		
}
