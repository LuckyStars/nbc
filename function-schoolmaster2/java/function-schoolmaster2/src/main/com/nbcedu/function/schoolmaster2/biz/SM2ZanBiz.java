package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
import com.nbcedu.function.schoolmaster2.vo.ZanVo;

public interface SM2ZanBiz extends BaseBiz<Sm2Zan>{

	Sm2Zan findByProgUid(String progId,String uid);
	
	/**
	 * 
	 * @param progId
	 * @param size puts 'null' if you want all results
	 * @return
	 * @author xuechong
	 */
	List<ZanVo> findByProg(String progId,Integer size);
	
	void removeByProg(String progId);
}
