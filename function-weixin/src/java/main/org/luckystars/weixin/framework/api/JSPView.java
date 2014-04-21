package org.luckystars.weixin.framework.api;

/**
 * 
 * @author xuechong
 */
@SuppressWarnings("serial")
public class JSPView implements View{

	protected String url;

	protected boolean redirect;
	
	public JSPView(String url, boolean redirect) {
		super();
		this.url = url;
		this.redirect = redirect;
	}

	@Override
	public ViewType getViewType() {
		return ViewType.JSP;
	}
	@Override
	public String toViewString() {
		return url;
	}
	///////////////////////////////////
	//////GETTERS&SETTERS///////
	///////////////////////////////
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
