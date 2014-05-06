package com.nbcedu.function.syllabus.devcore.util.exl;

import java.util.List;
import java.util.Map;

import com.nbcedu.function.syllabus.devcore.dao.HibernateBaseDao;
import com.nbcedu.function.syllabus.devcore.util.exl.process.QueryResultProcessor;



public class ExlQueryUtil {
	
	@SuppressWarnings("unchecked")
	private HibernateBaseDao dao;
	@SuppressWarnings("unchecked")
	public ExlQueryUtil(HibernateBaseDao dao) throws NullPointerException{
		super();
		if(dao==null){
			throw new NullPointerException("dao is null");
		}
		this.dao = dao;
	}
	
	/**
	 * 按HQL查询并导出
	 * @param head
	 * @param conditions
	 * @param hql
	 * @param params
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public void exportByHql(String head,List<String> conditions,String hql,List<Object> params){
		List<Map> result = this.dao.findMapList(hql, params);
		QueryResultProcessor.Process(head, conditions, result, hql);
	}
	/**
	 * 按HQL查询并导出
	 * @param head
	 * @param conditions
	 * @param hql
	 * @param params
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public void exportByHql(String hql,List<Object> params){
		List<Map> result = this.dao.findMapList(hql, params);
		QueryResultProcessor.Process(null, null, result, hql);
	}
	/**
	 * 按HQL查询并导出
	 * @param head
	 * @param conditions
	 * @param hql
	 * @param params
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public void exportByHql(String head,String hql,List<Object> params){
		List<Map> result = this.dao.findMapList(hql, params);
		QueryResultProcessor.Process(head, null, result, hql);
	}
	/**
	 * 按HQL查询并导出
	 * @param head
	 * @param conditions
	 * @param hql
	 * @param params
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public void exportByHql(List<String> conditions,String hql,List<Object> params){
		List<Map> result = this.dao.findMapList(hql, params);
		QueryResultProcessor.Process(null, conditions, result, hql);
	}
	/**
	 * 按SQL查询并导出
	 * @param head
	 * @param conditions
	 * @param sql
	 * @param params
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public void exportBySql(String head,List<String> conditions,String sql, List<Object> params){
		List<Map> result = this.dao.findMapListBySQL(sql, params);
		QueryResultProcessor.Process(head, conditions, result, sql);
	}
	
	@SuppressWarnings("unchecked")
	public void exportBySql(String sql, List<Object> params){
		List<Map> result = this.dao.findMapListBySQL(sql, params);
		QueryResultProcessor.Process(null, null, result, sql);
	}
	
	@SuppressWarnings("unchecked")
	public void exportBySql(String head,String sql, List<Object> params){
		List<Map> result = this.dao.findMapListBySQL(sql, params);
		QueryResultProcessor.Process(head, null, result, sql);
	}
	
	@SuppressWarnings("unchecked")
	public void exportBySql(List<String> conditions,String sql, List<Object> params){
		List<Map> result = this.dao.findMapListBySQL(sql, params);
		QueryResultProcessor.Process(null, conditions, result, sql);
	}
}
