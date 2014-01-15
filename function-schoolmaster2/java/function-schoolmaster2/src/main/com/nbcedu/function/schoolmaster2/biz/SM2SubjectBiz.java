package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;
import java.util.Map;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

public interface SM2SubjectBiz extends BaseBiz<TSm2Subject>{
	/**
	 * 分页查找主题
	 * @param subject
	 * @return
	 */
	public PagerModel findByModel(TSm2Subject subject);
	
	public PagerModel findByCreaterId(SubjectVo subject);
	
	public PagerModel findByExceuteUserId(SubjectVo subject);
	
	public PagerModel findByModule(String moduleId);
	
	public List<TSm2Subject> findByTypeUser(String userId,String typeId,String moduleId);
	
	public List<TSm2Subject> findByModuleIdExceuteUserId(String moduleId,String exceuteUserId);
	
	public void update(TSm2Subject subject);
	/**
	 * 根据校长查询subject列表
	 * @param subject
	 * @return
	 */
	PagerModel findBySubjectMaster(SubjectVo subject);
	/**
	 * 修改插旗状态
	 * @param flag
	 * @param subId
	 * @param userUid
	 * @throws DBException
	 */
	public void updateMasterFlag(int flag, String subId,String userUid) throws DBException ;

	PagerModel findAlltrans(SubjectVo subject,String curUserId) throws DBException;
	/**
	 * 修改所有subject的flag
	 * @param flag
	 * @param subId
	 * @throws DBException
	 */
	void updateMasterFlagAll(int flag, String subId) throws DBException;
	
	public List<Map<String,String>> findStatusCount(String userId);
	
	TSm2Subject findSubByStepId(String stepId);
	
}
