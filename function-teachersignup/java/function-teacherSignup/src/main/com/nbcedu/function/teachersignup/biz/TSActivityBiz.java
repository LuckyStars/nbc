package com.nbcedu.function.teachersignup.biz;

import java.util.List;

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
}
