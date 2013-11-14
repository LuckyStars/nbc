package com.nbcedu.function.schoolmaster2.biz;

import java.util.Collection;
import java.util.List;

import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.MasterSubSearchVO;
import com.nbcedu.function.schoolmaster2.vo.StepVo;
import com.nbcedu.function.schoolmaster2.vo.SubWeekSearch;
import com.nbcedu.function.schoolmaster2.vo.SubjectWeekVo;

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
	 * 按校长和模块类型查询前 size个内容
	 * @param modId
	 * @param masterUid 如果为null 或 "" 则查询所有
	 * @param size
	 * @return
	 * @author xuechong
	 */
	public List<TSm2Subject> findByMasterAndCount(String modId,String masterUid,Integer size);
	
	/**
	 * 按subjectId查找所有步骤
	 * @param subId
	 * @return
	 * @author xuechong
	 */
	public List<StepVo> findAllSteps(String subId);
	
	/**
	 * 按条件分页
	 * @param vo
	 * @param uid 接受人的uid
	 * @return
	 * @author xuechong
	 */
	public PagerModel findBySearchVo(MasterSubSearchVO vo);
	
	public List<TSm2Subject> findByMsterModule(String masterUid,Collection<String> moduleId,Integer size);

	
	public List<SubjectWeekVo> findWeek(SubWeekSearch search);
	
	public List<SubjectWeekVo> findWeekSingle(SubWeekSearch search);
}
