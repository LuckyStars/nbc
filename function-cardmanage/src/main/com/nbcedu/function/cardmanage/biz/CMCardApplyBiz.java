package com.nbcedu.function.cardmanage.biz;

import com.nbcedu.function.cardmanage.core.biz.BaseBiz;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.vo.CMApply;

public interface CMCardApplyBiz extends BaseBiz<CMCardApply>{
	
	public PagerModel findAllBy(CMApply cmApply,String userUid);
	/**
	 * 增加申请
	 * @param cmApply
	 * @return
	 */
	public boolean add(CMApply cmApply);
	boolean modifyApply(CMApply cmApply);
	PagerModel findAllManageBy(CMApply cmApply);
}
