package com.nbcedu.function.teachersignup.biz;

import java.util.List;

import com.nbcedu.function.teachersignup.constants.ActStatus;
import com.nbcedu.function.teachersignup.core.biz.BaseBiz;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.model.TSActivity;

public interface TSActivityBiz extends BaseBiz<TSActivity>{
	
	public void addOrUpdate(TSActivity activity,String[] subjects,String[] rewards);
	/**
	 * 查看报名状态
	 */
	public List<TSActivity> findAll() ;
	
	public PagerModel findAllByPage();
	
	public PagerModel findByMonthStatus(Integer month,Integer Status);
	
	public void modifyStatus(String id,ActStatus status);
	
	public void modifyFinActs();
	
	public void removeActById(String id);
	
	public List<TSActivity> findByStatus(ActStatus status);
	/**
	 * 查询所有已暂停和结束的活动
	 * @return
	 * @author xuechong
	 */
	public PagerModel findFinished();
}
