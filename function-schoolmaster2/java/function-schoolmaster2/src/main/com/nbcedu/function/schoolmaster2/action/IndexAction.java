package com.nbcedu.function.schoolmaster2.action;

import java.sql.SQLException;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.util.HibernateDao;

/**
 * 
 * @author xuechong
 */
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{
	
	private String rightURL;
	private static final Logger logger = Logger.getLogger(IndexAction.class);
	private String photoPath ;
	private HibernateDao dao;
	
	public String index(){
		this.photoPath = this.getPhoto();
		if(logger.isInfoEnabled()){
			logger.info(photoPath);
		}
		return "index";
	}
	
	public String home(){
		return "home";
	}
	
	public String teacherInput(){
		return "teacherInput";
	}
	
	private String getPhoto(){
		final String uid = this.getUserId();
		String photo = (String) dao.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query q = session.getNamedQuery("index_photo");
				q.setString("uid", uid);
				return q.uniqueResult();
			}
		});
		return StringUtils.trimToEmpty(photo);
	}
	////////////////////////////////
	/////getters&setters//////
	/////////////////////////////
	public String getRightURL() {
		return rightURL;
	}
	public void setRightURL(String rightURL) {
		this.rightURL = rightURL;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	
}
