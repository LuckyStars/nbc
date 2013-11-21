package com.nbcedu.function.schoolmaster2.data.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.function.schoolmaster2.data.interfaces.AbstractDataGenerator;
import com.nbcedu.function.schoolmaster2.data.interfaces.ChartClass;
import com.nbcedu.function.schoolmaster2.data.interfaces.Matcher;
import com.nbcedu.function.schoolmaster2.data.vo.SingleCharts;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@Matcher("logistics")
public class LogisticsDataGenerator extends AbstractDataGenerator{

	@Override
	@SuppressWarnings({ "unchecked", "serial" })
	public String getDataByTime(Date start, Date end,String color)  {
		
		final Date st = start!=null?start:Utils.Dates.safeParseSimpleDate("2000-01-01");
		final Date en = end!=null?end:new Date();
				
		final List<Object[]> resultSet = getHibernateDao().getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.getNamedQuery("logistics_query").
					setDate("start",st).setDate("end", en).list();
			}
		});
		
		SingleCharts xmlData = new SingleCharts();
		xmlData.setCaption("电教预约");
		xmlData.setNumberPrefix("");
		xmlData.setSubcaption("电教预约统计");
		xmlData.setxAxisName("预约次数");
		xmlData.setyAxisName("预约类型");
		xmlData.setBgColor(color);
		xmlData.setDatas(new ArrayList<SingleCharts.DataSet>(){{
			for (Object[] obj : resultSet) {
				SingleCharts.DataSet dataSet = new SingleCharts.DataSet();
				dataSet.setName(obj[1].toString());
				dataSet.setValue(obj[0].toString());
				add(dataSet);
			}
		}});
		
		return xmlData.toXmlString();
	}

	@Override
	public String defaultChartType() {
		return ChartClass.Single.Pie2D.getName();
	}

}
