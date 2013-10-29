package com.nbcedu.function.schoolmaster2.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.nbcedu.function.schoolmaster2.utils.Utils;

/**
 * display content if curUser is headmaster
 * @author xuechong
 */
public class HeadMasterTag extends TagSupport{
	
	@Override
	public int doStartTag() throws JspException {
		for (PersonVo person : Utils.getAllSchoolMaster()) {
			if(person.getUid().equalsIgnoreCase(Utils.curUserUid())){
				return EVAL_PAGE;
			}
		}
		return SKIP_BODY;
	}
	
}
