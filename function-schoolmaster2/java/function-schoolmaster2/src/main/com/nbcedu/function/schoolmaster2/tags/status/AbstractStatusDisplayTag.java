package com.nbcedu.function.schoolmaster2.tags.status;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


@SuppressWarnings("serial")
public abstract class AbstractStatusDisplayTag<T extends StatusEnum> extends TagSupport{

	protected Integer statusId;

	@Override
	public int doStartTag() throws JspException {
		if(statusId!=null){
			T status = this.findById(statusId);
			if(status!=null){
				try {
					this.pageContext.getOut().write(status.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return SKIP_BODY;
	}
	
	protected abstract T findById(Integer id);
	
	//////////////////////
	///getters&setters////
	//////////////////////
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
}
