/*
 * @Title: ProfileAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: 配置管理的请求控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-19 下午03:39:10
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-19                          
 */
package com.nbcedu.function.documentflow.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.nbcedu.common.json.JSONTool;
import com.nbcedu.function.documentflow.biz.DocumentFlowPrivilegeBiz;
import com.nbcedu.function.documentflow.biz.DocumentSourceBiz;
import com.nbcedu.function.documentflow.biz.DocumentUserRoleBiz;
import com.nbcedu.function.documentflow.biz.NotifyProfileBiz;
import com.nbcedu.function.documentflow.biz.NotifyTimeBiz;
import com.nbcedu.function.documentflow.biz.TreeNodeBiz;
import com.nbcedu.function.documentflow.model.DoucmentUserRole;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.DocumentSourceVO;
import com.nbcedu.function.documentflow.vo.NotifyProfileVO;
import com.nbcedu.function.documentflow.vo.NotifyTimeVO;
import com.opensymphony.xwork2.ActionContext;

/** 
 * <p>配置管理的请求控制器</p>
 * @author Wang Zhuoxuan
 * Create at:2011-9-19 下午03:39:10
 */
public class ProfileAction {
	private DocumentSourceVO documentSourceVo;
	private List<DocumentSourceVO> documentSourceVoList;
	private List<NotifyProfileVO> notifyProfileVoList;
	private DocumentSourceBiz documentSourceBiz;
	private NotifyProfileBiz notifyProfileBiz;
	private NotifyTimeBiz notifyTimeBiz;
	private TreeNodeBiz treeNodeBiz;
	private DocumentFlowPrivilegeBiz documentFlowPrivilegeBiz;
	private DocumentUserRoleBiz documentUserRoleBiz;
	
	/** 
	 * 预加载一些信息并跳转到管理配置页
	 * v2
	 * @return 管理配置页对应的result名称
	 */ 
	public String display() {
		documentSourceVoList = documentSourceBiz.findDocumentSources();
		notifyProfileVoList = notifyProfileBiz.findNotifyProfiles();
		return "profile";
	}
	
	/** 
	 * 新建配置信息
	 */ 
	public void add() {
		NotifyProfileVO notifyProfileVo = new NotifyProfileVO();
		List<NotifyTimeVO> notifyTimeVoList = new ArrayList<NotifyTimeVO>();
		//接收请求参数
		String profileName = ServletActionContext.getRequest().getParameter("opVal");
		String isEdit = ServletActionContext.getRequest().getParameter("people");
		String[] notifyTimeIdArray = ServletActionContext.getRequest().getParameter("remind").split(",");
		String content = ServletActionContext.getRequest().getParameter("msgContent");
		
		notifyProfileVo.setIsDefault(false);
		notifyProfileVo.setIsEdit(Boolean.valueOf(isEdit));
		notifyProfileVo.setProfileName(profileName);
		notifyProfileVo.setContent(content);
		
		for (String notifyTimeId : notifyTimeIdArray) {
			notifyTimeVoList.add(notifyTimeBiz.findNotifyTime(notifyTimeId));
		}
		notifyProfileVo.setNotifyTimeVos(notifyTimeVoList.toArray(new NotifyTimeVO[notifyTimeVoList.size()]));
		
		String profileId = notifyProfileBiz.addNotifyProfile(notifyProfileVo);
		PrintWriter out = null;
		try {
			//将配置信息的唯一标识响应回客户端
			out = ServletActionContext.getResponse().getWriter();
			out.write(profileId);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
		
	}
	
	/** 
	 * 编辑提醒模式
	 * 
	 */ 
	public void edit() {
		String profileId = ServletActionContext.getRequest().getParameter("profileId");
		NotifyProfileVO profileVo = notifyProfileBiz.findNotifyProfile(profileId);
		Map<String, Object> profileJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> notifyTimeJsonList = new ArrayList<Map<String, Object>>();
		
		profileJsonMap.put("profileName", profileVo.getProfileName());
		profileJsonMap.put("isEditable", String.valueOf(profileVo.getIsEdit()));
		
		NotifyTimeVO[] notifyTimeArray = profileVo.getNotifyTimeVos();
		
		for (int i = 0; i < notifyTimeArray.length; i++) {
			Map<String, Object> notifyTimeJsonMap = new HashMap<String, Object>();
			notifyTimeJsonMap.put("id", notifyTimeArray[i].getId());
			notifyTimeJsonMap.put("value", notifyTimeArray[i].getDisplayName());
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			List<NotifyTimeVO> notifyTimeList;
			if (i == 0) {
				notifyTimeList = notifyTimeBiz.findNotifyTimes();
			} else {
				notifyTimeList = notifyTimeBiz.findNotifyTimes(notifyTimeArray[i - 1].getId());
			}
			
			for (NotifyTimeVO time : notifyTimeList) {
				Map<String, String> jsonMap = new HashMap<String, String>();
				if (!time.getId().equals(notifyTimeArray[i].getId())) {
					jsonMap.put("id", time.getId());
					jsonMap.put("value", time.getDisplayName());
					list.add(jsonMap);
				}
			}
			notifyTimeJsonMap.put("list", list);
			notifyTimeJsonList.add(notifyTimeJsonMap);
		}
		profileJsonMap.put("notifyTime", notifyTimeJsonList);
		profileJsonMap.put("content", profileVo.getContent());
		
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			//返回JSON结果
			JSONTool.writeJSONData(profileJsonMap, ServletActionContext.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 修改提醒模式信息
	 * 
	 */ 
	public void modify() {
		List<NotifyTimeVO> notifyTimeVoList = new ArrayList<NotifyTimeVO>();
		//接收请求参数
		String profileId = ServletActionContext.getRequest().getParameter("profileId");
		String profileName = ServletActionContext.getRequest().getParameter("opVal");
		String isEdit = ServletActionContext.getRequest().getParameter("people");
		String[] notifyTimeIdArray = ServletActionContext.getRequest().getParameter("remind").split(",");
		String content = ServletActionContext.getRequest().getParameter("msgContent");
		
		NotifyProfileVO notifyProfileVo = notifyProfileBiz.findNotifyProfile(profileId);
		
		notifyProfileVo.setIsEdit(Boolean.valueOf(isEdit));
		notifyProfileVo.setProfileName(profileName);
		notifyProfileVo.setContent(content);
		
		for (String notifyTimeId : notifyTimeIdArray) {
			notifyTimeVoList.add(notifyTimeBiz.findNotifyTime(notifyTimeId));
		}
		notifyProfileVo.setNotifyTimeVos(notifyTimeVoList.toArray(new NotifyTimeVO[notifyTimeVoList.size()]));
		
		notifyProfileBiz.modifyNotifyProfile(notifyProfileVo);
	}
	
	/** 
	 * 删除提醒模式
	 * 
	 */ 
	public void remove() {
		String profileId = ServletActionContext.getRequest().getParameter("profileId");
		NotifyProfileVO profile = new NotifyProfileVO();
		profile.setId(profileId);
		boolean delresult = notifyProfileBiz.removeNotifyProfile(profile);
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.print(delresult);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
	}
	
	/** 
	 * 加载提醒时间列表
	 * 
	 */ 
	public void initTimeList() {
		String notifyTimeId = ServletActionContext.getRequest().getParameter("ntid");
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<NotifyTimeVO> notifyTimeVoList;
		
		if ("".equals(notifyTimeId) || notifyTimeId == null) {
			notifyTimeVoList = notifyTimeBiz.findNotifyTimes();
		} else {
			notifyTimeVoList = notifyTimeBiz.findNotifyTimes(notifyTimeId);
		}
		
		try {
			//构造JSON数组
			for (NotifyTimeVO notifyTimeVo : notifyTimeVoList) {
				Map<String, String> option = new HashMap<String, String>();
				option.put("id", notifyTimeVo.getId());
				option.put("value", notifyTimeVo.getDisplayName());
				
				result.add(option);
			}
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			//返回JSON结果
			JSONTool.writeJSONData(result, ServletActionContext.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 添加发文单位
	 * 
	 */ 
	public void addDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setDisplayName(ServletActionContext.getRequest().getParameter("dsname"));
		String documentSourceId = documentSourceBiz.addDocumentSource(documentSourceVo);
		PrintWriter out =null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.write(documentSourceId);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
	}
	
	/** 
	 * 删除发文单位
	 * 
	 */ 
	public void removeDocumentSource() {
		documentSourceVo = new DocumentSourceVO();
		documentSourceVo.setId(ServletActionContext.getRequest().getParameter("dsid"));
		boolean delresult = documentSourceBiz.removeDocumentSource(documentSourceVo);
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.print(delresult);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
		
	}
	
	/** 
	 * 加载JSON树
	 * v2
	 */ 
	public void displayTree() {
		String pid = ServletActionContext.getRequest().getParameter("pid");
		String pids = ServletActionContext.getRequest().getParameter("pids");
		List<String> exceptedList = new ArrayList<String>();
		if (!StringUtil.isBlank(pid)) {
			exceptedList.add(pid);
		}
		if (!StringUtil.isBlank(pids)) {
			String[] exceptedArray = pids.split(",");
			exceptedList.addAll(Arrays.asList(exceptedArray));
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			JSONTool.writeJSONData(treeNodeBiz.findAllNodes(exceptedList), ServletActionContext.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 异步设置人员权限
	 * v2
	 */ 
	public void setUpPrivilege() {
		String[] pids = ServletActionContext.getRequest().getParameter("pids").split(",");
		String ptype = ServletActionContext.getRequest().getParameter("ptype");
		
		if (pids != null) {
			documentFlowPrivilegeBiz.modifyPrivilege(ptype, pids);
		}
	}

	/** 
	 * 加载权限的人员列表
	 * v2
	 * @return 跳转至权限页面 
	 */ 
	public String displayPrivilege() {
		List<DoucmentUserRole> publisherUserRoles = documentUserRoleBiz.findUserRoles("DOCUMENTFLOW_PUBLISHER");
		List<DoucmentUserRole> transferUserRoles = documentUserRoleBiz.findUserRoles("DOCUMENTFLOW_TRANSFERER");
		List<DoucmentUserRole> adminUserRoles = documentUserRoleBiz.findUserRoles("DOCUMENTFLOW_ADMIN");
		
		ActionContext.getContext().put("publishers", publisherUserRoles);
		ActionContext.getContext().put("transferers", transferUserRoles);
		ActionContext.getContext().put("admins", adminUserRoles);
		return "privilege";
	}
	
	
	//////////////////////
	////GETTERS&SETTERS///
	/////////////////////
	public DocumentSourceVO getDocumentSourceVo() {
		return documentSourceVo;
	}
	public void setDocumentSourceVo(DocumentSourceVO documentSourceVo) {
		this.documentSourceVo = documentSourceVo;
	}
	public List<DocumentSourceVO> getDocumentSourceVoList() {
		return documentSourceVoList;
	}
	public void setDocumentSourceVoList(List<DocumentSourceVO> documentSourceVoList) {
		this.documentSourceVoList = documentSourceVoList;
	}
	public List<NotifyProfileVO> getNotifyProfileVoList() {
		return notifyProfileVoList;
	}
	public void setNotifyProfileVoList(List<NotifyProfileVO> notifyProfileVoList) {
		this.notifyProfileVoList = notifyProfileVoList;
	}
	public void setDocumentSourceBiz(DocumentSourceBiz documentSourceBiz) {
		this.documentSourceBiz = documentSourceBiz;
	}
	public void setNotifyProfileBiz(NotifyProfileBiz notifyProfileBiz) {
		this.notifyProfileBiz = notifyProfileBiz;
	}
	public void setNotifyTimeBiz(NotifyTimeBiz notifyTimeBiz) {
		this.notifyTimeBiz = notifyTimeBiz;
	}
	public void setTreeNodeBiz(TreeNodeBiz treeNodeBiz) {
		this.treeNodeBiz = treeNodeBiz;
	}
	public void setDocumentFlowPrivilegeBiz(DocumentFlowPrivilegeBiz documentFlowPrivilegeBiz) {
		this.documentFlowPrivilegeBiz = documentFlowPrivilegeBiz;
	}
	public DocumentUserRoleBiz getDocumentUserRoleBiz() {
		return documentUserRoleBiz;
	}
	public void setDocumentUserRoleBiz(DocumentUserRoleBiz documentUserRoleBiz) {
		this.documentUserRoleBiz = documentUserRoleBiz;
	}
}
