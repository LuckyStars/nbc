package com.nbcedu.function.schoolmaster2.biz;

import java.text.ParseException;
import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;

public interface SM2InvatitionBiz extends BaseBiz<TSm2Invatition>{
	public PagerModel findByCreaterId(String createrId, String createtime,String title,String user) throws ParseException;
	public List<TSm2Invatition> findInvatIds(String createrId) throws ParseException;
	public List<TSm2Invatition> findCreaterIds(String invatId) throws ParseException;
	public PagerModel findByInvatId(String invatId, String createtime,String title,String user) throws ParseException;
}
