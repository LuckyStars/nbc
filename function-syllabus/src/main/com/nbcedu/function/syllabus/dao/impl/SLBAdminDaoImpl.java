package com.nbcedu.function.syllabus.dao.impl;

import org.springframework.stereotype.Repository;

import com.nbcedu.function.syllabus.dao.SLBAdminDao;
import com.nbcedu.function.syllabus.devcore.dao.impl.ExtendsHibernateBaseDaoImpl;
import com.nbcedu.function.syllabus.model.SLBAdmin;

@Repository("slbAdminDao")
public class SLBAdminDaoImpl extends ExtendsHibernateBaseDaoImpl<SLBAdmin> implements SLBAdminDao{

}
