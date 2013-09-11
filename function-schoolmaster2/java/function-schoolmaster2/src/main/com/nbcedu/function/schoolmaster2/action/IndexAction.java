package com.nbcedu.function.schoolmaster2.action;

import org.apache.log4j.Logger;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.utils.Utils;

/**
 * 
 * @author xuechong
 */
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{
	
	private static final Logger logger = Logger.getLogger(IndexAction.class);
	public String index(){
//		logger.info(Utils.curUserUid());
		return "index";
	}
	
}
