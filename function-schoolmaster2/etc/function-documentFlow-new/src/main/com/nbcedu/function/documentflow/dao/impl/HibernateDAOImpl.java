package com.nbcedu.function.documentflow.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.function.documentflow.dao.HibernateDAO;

/** 
 * <p>BaseDAO实现类</p>
 * @author Wang Zhuoxuan
 * Create at:2011-8-3 下午02:06:35
 */
public class HibernateDAOImpl extends HibernateDaoSupport implements HibernateDAO {

	@Override
	public void create(Object obj) {
		getHibernateTemplate().save(obj);
	}

	@Override
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	@Override
	public void delete(Class<?> clazz, Serializable id) {
		getHibernateTemplate().delete(load(clazz, id));
	}

	@Override
	public Integer delete(final String queryString, final Object[] params) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return Integer.valueOf(query.executeUpdate());
			}
		});
	}

	@Override
	public Integer deleteAll(final Class<?> clazz) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery("DELETE " + clazz.getName());
				return Integer.valueOf(query.executeUpdate());
			}
		});
	}

	@Override
	public Object get(Class<?> clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Object get(String queryString, Object[] params) {
		List<?> list = getHibernateTemplate().find(queryString, params); 
		if (list != null && !list.isEmpty()) { 
			return list.get(0); 
		} 
		return null;
	}

	@Override
	public Object load(Class<?> clazz, Serializable id) {
		return getHibernateTemplate().load(clazz, id);
	}

	@Override
	public List<?> retrieve(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	@Override
	public List<?> retrieve(String queryString, Object[] params) {
		return getHibernateTemplate().find(queryString, params); 
	}

	@Override
	public List<?> retrieveAll(Class<?> clazz) {
		return getHibernateTemplate().find("FROM " + clazz.getName());
	}

	@Override
	public int retrieveCountByQuery(final String queryString, final Object[] params) {
		List list =  (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				logger.info(query.toString());
				return query.list();
				}
		});
		if(CollectionUtils.isEmpty(list)){return 0;}
		return list.size();
	}
	@Override
	public List<?> retrievePageByQuery(final String queryString, final Object[] params, final PagerUtils pagerUtils) {
		return (List<?>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				ScrollableResults sr = query.scroll();
				sr.last();
				int totalCount = sr.getRowNumber();
				int startIndex = (pagerUtils.getPageIndex() - 1) * pagerUtils.getPageSize();
				query.setMaxResults(pagerUtils.getPageSize());
				query.setFirstResult(startIndex);
				int totalResult = totalCount + 1;
				buildPager(pagerUtils,totalResult);
				return query.list();
			}
		});
	}
	@Override
	public List<?> retrievePageByQuery(final String queryString, final int status,final int num) {
		return (List<?>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
						query.setParameter(0, status);
				query.setMaxResults(num);
				query.setFirstResult(0);
				return query.list();
			}
		});
	}

	@Override
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	@Override
	public Integer batchCreate(final List<?> objList, final int batchSize) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				for (int i = 0; i < objList.size(); i++) {
					session.save(objList.get(i));
					if (i % batchSize == 0) {
						session.flush();
						session.clear();
					}
				}
				return null;
			}
		});
	}

	@Override
	public void update(final String queryString, final Object[] params) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(queryString);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	public void buildPager(final PagerUtils pager, Integer maxResults) {
		int totalPage = (maxResults % pager.getPageSize() == 0) ? 
				(maxResults / pager.getPageSize()) 
				: (maxResults / pager.getPageSize()) + 1;
		int[] pageNumbers = new int[totalPage];
		for (int i = 0; i < totalPage; i++) {
			pageNumbers[i] = (i + 1);
		}
		pager.setTotalResult(maxResults);
		pager.setPageNumbers(pageNumbers);
		pager.setTotalPage(totalPage);
		pager.setPageSize(pager.getPageSize());
		pager.setPageIndex(pager.getPageIndex());
		pager.setPrePage(pager.getPageIndex() - 1);
		pager.setNextPage(pager.getPageIndex() + 1);
	}
}
