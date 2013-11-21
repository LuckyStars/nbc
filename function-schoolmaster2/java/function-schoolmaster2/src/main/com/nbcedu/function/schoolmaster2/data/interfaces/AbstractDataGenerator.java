package com.nbcedu.function.schoolmaster2.data.interfaces;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.data.util.HibernateDao;
import com.nbcedu.function.schoolmaster2.utils.Utils;

public abstract class AbstractDataGenerator implements DataGenerator{
	
	private HibernateDao dao;
	
	protected HibernateDao getHibernateDao(){
		if(this.dao==null){
			this.dao = (HibernateDao)Utils.Beans.getSpringBeanByName("dataHibernateDao");
		}
		return this.dao;
	}
	
	@Override
	public String getDataByTime(Date start, Date end) {
		return getDataByTime(start, end, "FFFFFF");
	}
	
}
