package com.nbcedu.function.schoolmaster2.biz;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.MasterSubSearchVO;
import com.nbcedu.function.schoolmaster2.vo.StepVo;
import com.nbcedu.function.schoolmaster2.vo.SubWeekSearch;
import com.nbcedu.function.schoolmaster2.vo.SubjectWeekVo;
import com.nbcedu.function.schoolmaster2.vo.SubjectZanVo;

public interface SM2MasterSubBiz extends SM2SubjectBiz {

	/**
	 * 按校长和模块类型分页
	 * @param modId
	 * @param masterUid
	 * @return
	 * @author xuechong
	 */
	public PagerModel findByMaster(String modId,String masterUid);
	/**
	 * 
	 * @param modId
	 * @param masterUid
	 * @param flagType
	 * @return
	 * @author xuechong
	 */
	public PagerModel findByMaster(String modId,String masterUid,Integer flagType);
	/**
	 * 按校长和模块类型查询前 size个内容
	 * @param modId
	 * @param masterUid 如果为null 或 "" 则查询所有
	 * @param size
	 * @return
	 * @author xuechong
	 */
	public List<TSm2Subject> findByMasterAndCount(String modId,String masterUid,Integer first,Integer size);
	
	/**
	 * 按subjectId查找所有步骤
	 * @param subId
	 * @return
	 * @author xuechong
	 */
	public List<StepVo> findAllSteps(String subId);
	
	/**
	 * 根据步骤获取下面所有的评论
	 * @param stepId
	 */
	public List<TSm2Disscus> findDisscusByProgressId(String progressId,Integer firstResult,Integer size);
	
	/**
	 * 获取进展下的所有步骤
	 * @param stepId
	 */
	public List<TSm2Progress> findProgressByStepId(String stepId);
	
	/**
	 * 按条件分页
	 * @param vo
	 * @param uid 接受人的uid
	 * @return
	 * @author xuechong
	 */
	public PagerModel findBySearchVo(MasterSubSearchVO vo);
	
	public List<TSm2Subject> findByMsterModule(String masterUid,Collection<String> moduleId,Integer size);

	/**
	 * 本周工作汇总
	 * @param search
	 * @return
	 * @author xuechong
	 */
	public List<SubjectWeekVo> findWeek(SubWeekSearch search);
	/**
	 * 单个人的本周工作
	 * @param search
	 * @return
	 * @author xuechong
	 */
	public List<SubjectWeekVo> findWeekSingle(SubWeekSearch search);
	
	public SubjectZanVo findByProgId(String progId);
	
	/**
	 * 
	 * @param uid
	 * @return <code>Map&lt;String,Integer&gt;</code>
	 * <br>key: moduleId | value : count
	 * @author xuechong
	 */
	public Map<String,Integer> findNewCountByModule(String uid);
	
	/**
	 * 查找一个moduleId下 所有type的更新数量
	 * @param moduleId
	 * @param uid
	 * @return
	 * @author xuechong
	 */
	public Map<String,Integer> findAttCountByModType(String moduleId,String uid);
	/**
	 * 更改执行者阅读状态；
	 */
	void updateMasterUserStatus(String userId,String subjectId)  throws DBException;
	
	public void deleteBySubId(String Id);
}
