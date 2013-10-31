package com.nbcedu.function.schoolmaster2.tags.display;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public abstract class AbstractDisplayTag extends TagSupport{
	
	@Override
	public int doStartTag() throws JspException {
		return display()?EVAL_BODY_INCLUDE:SKIP_BODY;
	}
	
	/**
	 * @return TRUE if the content should be displayed
	 * @author xuechong
	 */
	protected abstract boolean display();
	
}
