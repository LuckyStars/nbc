package com.nbcedu.function.teachersignup.biz;

import java.util.List;

import com.nbcedu.function.teachersignup.core.biz.BaseBiz;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.vo.TSUser;

public interface TSSignBiz extends BaseBiz<TSSign>{

	void addNewSign(TSUser tsUser, String[] subjectIds);
	
	/**
	 * search page by actId
	 * @param actId
	 * @return
	 * @author xuechong
	 */
	PagerModel pageByAct(String actId,String subId,String rewId,String userName);

	List<TSSign> findAllByUidActId(String uid,String actId);
	
	public void addRew(String signId,String rewId);
	
	public List<TSSign> findByActId(String actId);
	
}
