package com.nbcedu.function.cardmanage.core.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nbcedu.function.cardmanage.constants.Constants;
import com.nbcedu.function.cardmanage.core.pager.Pager;
import com.nbcedu.function.cardmanage.core.pager.PagerModel;
import com.nbcedu.function.cardmanage.vo.CMUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p></p>
 * @author 黎青春
 * Create at:2012-2-21 上午08:49:19
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	HttpServletResponse response = ServletActionContext.getResponse();     
	public static Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * 当前用户UID
	 * @return
	 * @author xuechong
	 */
	protected String getCurUserUid(){
		return (String) ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY);
	}
	
	protected Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	protected CMUser getCurUser(){
		return (CMUser) getSession().get(Constants.SESSION_USER_KEY);
	}
	/**
	 * 默认的分页对象
	 */
	protected Pager pager = new Pager();
	
	protected PagerModel pm = new PagerModel();
	
	protected String id;
	
	protected List<String> idList = new ArrayList<String>();
	
//	protected FunctionSupportUtil functionSupportUtil = SupportManager.getFunctionSupportUtil();
	/**
	 * 进行增删改操作后,以redirect方式重新打开action默认页的result名
	 */
	public static final String RELOAD = "reload";
	/**
	 * 显示列表
	 */
	public static final String LIST = "list";
	/**
	 * 查看详细
	 */
	public static final String LOOK = "look";
	/**
	 * 排序
	 */
	protected String ORDERBYID ="ORDER BY id DESC";
	
	//////////////////////////
	///////getters&setters////
	//////////////////////////
	public PagerModel getPm() {
		return pm;
	}
	public void setPm(PagerModel pm) {
		this.pm = pm;
	}
	public Pager getPager() {
		return pager;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	
}
