package com.nbcedu.function.schoolmaster2.data.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.function.schoolmaster2.data.interfaces.AbstractDataGenerator;
import com.nbcedu.function.schoolmaster2.data.model.BookSiteData;
import com.nbcedu.function.schoolmaster2.data.util.HibernateDao;
import com.nbcedu.function.schoolmaster2.data.vo.SingleCharts;
import com.nbcedu.function.schoolmaster2.utils.Utils;

public class BookSiteDataGenerator extends AbstractDataGenerator<BookSiteData>{

	private HibernateDao dao;

	@Override
	@SuppressWarnings("unchecked")
	public BookSiteData getDataByTime(Date start, Date end)  {
		
		final Date st = start!=null?start:Utils.Dates.safeParseSimpleDate("2000-01-01");
		final Date en = end!=null?end:new Date();
				
		List<Object[]> resultSet = dao.getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.getNamedQuery("bookSite_query").
					setDate("start",st).setDate("end", en).list();
			}
		});
		Map<String, String> result = new HashMap<String, String>();
		if(resultSet!=null&& resultSet.size() >0){
			for (Object[] obj : resultSet) {
				result.put(obj[1].toString(), obj[0].toString());
			}
		}
		
		BookSiteData data = new BookSiteData(new SingleCharts());
		
		return null;
	}

	@Override
	protected String getMatchStr() {
		return "bookSite";
	}

}
