package com.nbcedu.function.teachersignup.biz.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.constants.ActStatus;
import com.nbcedu.function.teachersignup.constants.Constants;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.core.exception.DBException;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.core.util.date.DateUtil;
import com.nbcedu.function.teachersignup.dao.TSActivityDao;
import com.nbcedu.function.teachersignup.dao.TSRewardDao;
import com.nbcedu.function.teachersignup.dao.TSSubjectDao;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSubject;
import com.nbcedu.function.teachersignup.util.Utils;
import com.nbcedu.function.teachersignup.vo.TSUser;
import com.opensymphony.xwork2.ActionContext;


public class TSActivityBizImpl extends BaseBizImpl<TSActivity> implements TSActivityBiz{
	
	private static final Logger logger = Logger.getLogger(TSActivityBizImpl.class);
	private TSActivityDao actDao;
	private TSRewardDao rewardDao;
	private TSSubjectDao subjectDao;
	
	public void setActDao(TSActivityDao actDao) {
		super.setDao(actDao);
		this.actDao = actDao;
	}

	@Override
	public void addOrUpdate(TSActivity act, String[] subs,String[] rews) {
		if(StringUtils.isNotBlank(act.getId())){
			String fileName = act.getFileName();
			String filePath = act.getFilePath();
			String  comment = act.getComment();
			this.actDao.createQuery("DELETE FROM TSReward r WHERE r.activityId=?",act.getId()).executeUpdate();
			this.actDao.createQuery("DELETE FROM TSSubject s WHERE s.activityId=?", act.getId());
			act = this.findById(act.getId());
		
			if(StringUtils.isNotEmpty(fileName)){
				act.setFileName(fileName);
				act.setFilePath(filePath);
			}
			if(StringUtils.isNotEmpty(comment)){
				act.setComment(comment);
			}
		}else{
			act.setId(null);
		}
		
		act.setSubjects(new HashSet<TSSubject>());		
		act.setRewards(new HashSet<TSReward>());
		
		if(rews!=null&&rews.length>0){
			for (String re :rews) {
				if(StringUtils.isNotBlank(re)){
					TSReward rewa = new TSReward();
					try {
						rewa.setName(URLDecoder.decode(re,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error(e);
					}
					act.getRewards().add(rewa);
				}
			}
		}
		
		if(subs!=null&&subs.length>0){
			for(String su:subs){
				if(StringUtils.isNotBlank(su)){
					TSSubject subj = new TSSubject();
					try {
						subj.setName(URLDecoder.decode(su, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error(e);
					}
					act.getSubjects().add(subj);
				}
			}
		}
//		if(StringUtils.isNotEmpty(act.getId())){
//			addHSIPost(act);
//		}
		this.actDao.saveOrUpdate(act);
	}
	
	public List<TSActivity> findAll(){
		return actDao.getAll();
	}
	
	@Override
	public PagerModel findAllByPage() {
		return this.actDao.searchPaginated("FROM TSActivity a ORDER BY a.createDate DESC");
	}
	
	@Override
	public PagerModel findByMonthStatus(Integer month, Integer status) {
		Criteria cri = this.actDao.createCriteria();
		
		if(month!=null && month >0){
			Date beforeDate = new Date(
					new Date().getTime() - 1000L*60L*60L*24L*30L*month.longValue()
			);
			cri.add(
				Restrictions.gt("createDate", beforeDate)		
			);
		}
		
		if(status!=null&&ActStatus.findById(status)!=null){
			cri.add(Restrictions.eq("status", status));
		}
		
		cri.addOrder(Order.desc("createDate"));
		return this.actDao.searchPaginated(cri);
	}
	
	@Override
	public void modifyStatus(String id, ActStatus status) {
		
		this.actDao.createQuery("UPDATE TSActivity a SET a.status=? WHERE a.id=?",
				status.getId(),id).executeUpdate();

//		if (status.getId() == ActStatus.PUBLISHED.getId()) {
			TSActivity act = this.findById(id);
			if (act != null) {
				act.setStatus(status.getId());
				this.addHSIPost(act);
			}
//		}
	}
	
	@Override
	public void modifyFinActs() {
		List<Object[]> list;
		try {
			list = this.actDao.findBySQL("select * from t_teachersignup_activity a  WHERE a.status=1 and a.end_Date < str_to_date('"+DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss")+"','%Y-%m-%d %H:%i:%s')");
			this.actDao.createQuery("UPDATE TSActivity a SET a.status = ? WHERE a.endDate < ?",
					ActStatus.FINISHED.getId(),new Date() ).executeUpdate();
			
			for(Object[] t: list){
				TSActivity act = new TSActivity();
				act.setId((String)t[0]);
				act.setFilePath((String)t[1]);
				act.setFileName((String)t[2]);
				act.setComment((String)t[3]);
				act.setOpenDate((Date)t[4]);
				act.setEndDate((Date)t[5]);
				act.setCreateDate((Date)t[6]);
				act.setStatus(3);
				act.setName((String)t[8]);
				Utils.Message.sendAddMsg(act);
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
	
		
	}
	
	@Override
	public TSActivity removeById(Serializable id) {
		return super.removeById(id);
	}
	
	@Override
	public void removeActById(String id) {
		TSActivity act = this.findById(id);
		this.actDao.createQuery("DELETE FROM TSSign s WHERE s.subjectId in (FROM TSSubject sub WHERE sub.activityId=?)", id).executeUpdate();
		if(act!=null){
			if(act.getRewards()!=null){
				for (TSReward rew : act.getRewards()) {
					this.rewardDao.remove(rew);
				}
			}
			if(act.getSubjects()!=null){
				for (TSSubject sub : act.getSubjects()) {
					this.subjectDao.remove(sub);
				}
			}
		}
		
		this.removeById(id);
	}
	
	@Override
	public List<TSActivity> findByStatus(ActStatus status) {
		return this.actDao.findBy("status", status.getId());
	}

	@Override
	public PagerModel findFinished() {
		Criteria cri = this.actDao.createCriteria(
				Restrictions.in("status", 
				new Integer[]{ActStatus.FINISHED.getId(),ActStatus.PAUSED.getId()}));
		cri.addOrder(Order.desc("endDate"));
		return this.actDao.searchPaginated(cri);
	}
	
	public void addHSIPost (TSActivity act){
//		if(act.getStatus() != ActStatus.PUBLISHED.getId()){
//			return;
//		}
		Utils.Message.sendAddMsg(act);
		TSUser curUser = (TSUser)ActionContext.getContext().getSession().get(Constants.SESSION_USER_KEY);
		String sql = Utils.Message.getInsertSQL(act, curUser.getUserUid(),curUser.getUserName());
		this.removeHSOPost(act.getId());
		this.actDao.createSqlQuery(sql).executeUpdate();
	}
	
	private void removeHSOPost(String id){
		this.actDao.createSqlQuery("DELETE FROM T_HSI_POST WHERE PK_T_HSI_POST_POSTID = ?", id).executeUpdate();
	}
}
