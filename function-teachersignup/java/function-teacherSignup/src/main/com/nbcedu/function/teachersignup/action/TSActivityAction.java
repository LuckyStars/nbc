package com.nbcedu.function.teachersignup.action;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.constants.ActStatus;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.core.util.struts2.Struts2Utils;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSSubject;
import com.nbcedu.function.teachersignup.util.Utils;
import com.nbcedu.function.teachersignup.vo.TSActivityVO;



/**
 * 报名事件ACTION
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSActivityAction extends BaseAction{
	
	private TSActivityBiz actBiz;
	
	private File atta;
	private String attaFileName;
	private String subjectName;
	private String rewardName;
	private TSActivity act;
	
	private Integer month;//search month
	private Integer actStatu;//search status

	/***普通教师活动列表 已开放*/
	List<TSActivityVO> openList = new LinkedList<TSActivityVO>();
	/***普通教师活动列表 未开放*/
	List<TSActivityVO> waitList = new LinkedList<TSActivityVO>();
	/**普通教师活动列表(已结束)**/
	List<TSActivity> finList  = new ArrayList<TSActivity>();
	/**
	 * 新增
	 * @return
	 * @throws IOException
	 * @author xuechong
	 */
	public String add() throws IOException{
		if(atta!=null){
			String savePath = this.savePath(atta);
			FileUtils.copyFile(atta, new File(savePath));
			this.act.setFileName(this.attaFileName);
			this.act.setFilePath(savePath);
		}
		
		this.act.setComment(URLDecoder.decode(this.act.getComment(), "utf-8"));
		this.act.setName(URLDecoder.decode(this.act.getName(), "utf-8"));
		String[] sub = StringUtils.isNotBlank(subjectName)?subjectName.split(","):null;
		String[] rew = StringUtils.isNotBlank(rewardName)?rewardName.split(","):null;
		this.act.setStatus(ActStatus.EDITING.getId());
		this.act.setCreateDate(new Date());
		this.actBiz.addOrUpdate(act, sub, rew);
		return RELOAD_ADMIN;
	}
	
	private String savePath(File file){
		StringBuilder result = new StringBuilder(
				ServletActionContext.getServletContext().
				getRealPath("/function/function-teachersignup/upload"));
			
		result.append(File.separator);
		result.append(
				new SimpleDateFormat("yyyyMM").format(new Date())
		);
		
		result.append(new Date().getTime());
		result.append(file.getName());
		return result.toString();
	}
	
	public String gotoEdit(){
		this.act = this.actBiz.findById(this.id);
		return "edit";
	}
	/**
	 * 鹳狸猿列表
	 * @return
	 * @author xuechong
	 */
	public String adminList(){
		this.pm = this.actBiz.findByMonthStatus(month, actStatu);
		return "adminList";
	}
	
	/**
	 * 统计查看列表
	 * @return
	 * @author xuechong
	 */
	public String masterList(){
		this.adminList();
		return "masterList";
	}
	
	/**
	 * (鹳狸猿)删除
	 * @return
	 * @author xuechong
	 */
	public String remove(){
		this.actBiz.removeById(this.id);
		return this.adminList();
	}
	
	/**
	 * 手动取消
	 * @return
	 * @author xuechong
	 */
	public String manCancel(){
		this.act = this.actBiz.findById(this.id);
		if(act!=null&&act.getOpenDate().after(new Date())){
			this.actBiz.modifyStatus(this.id,ActStatus.EDITING);
		}
		return RELOAD_ADMIN;
	}
	
	/**
	 * 发布
	 * @return
	 * @author xuechong
	 */
	public String pub(){
		this.act = this.actBiz.findById(this.id);
		if(act!=null){
			this.actBiz.modifyStatus(this.id, ActStatus.PUBLISHED);
		}
		return RELOAD_ADMIN;
	}
	
	/**
	 * 暂停发布
	 * @return
	 * @author xuechong
	 */
	public String pause(){
		this.actBiz.modifyStatus(this.id, ActStatus.PAUSED);
		return RELOAD_ADMIN;
	}
	
	/**
	 * 普通列表
	 * @return
	 * @author xuechong
	 */
	public String commonList(){
		this.pm = this.actBiz.findByMonthStatus(null, ActStatus.PUBLISHED.getId());
		return "commonList";
	}
	
	/**
	 * 删除
	 * @return
	 * @author xuechong
	 */
	public String del(){
		this.actBiz.removeById(this.id);
		return RELOAD_ADMIN;
	}
	
	/**
	 * 显示所有正在进行的
	 * @return
	 * @author xuechong
	 */
	public String comListPubed(){
		this.waitList = new ArrayList<TSActivityVO>();
		this.openList = new ArrayList<TSActivityVO>();
		List<TSActivity> actList = this.actBiz.findByStatus(ActStatus.PUBLISHED);
		
		if (!isEmpty(actList)) {
			List<TSActivity> opList = new LinkedList<TSActivity>();
			List<TSActivity> wtList = new LinkedList<TSActivity>();
			Date now = new Date();
			for (TSActivity act : actList) {
				if (act.getOpenDate().before(now)) {
					opList.add(act);
				} else {
					wtList.add(act);
				}
			}
			this.waitList = TSActivityVO.Factory.build(wtList);
			this.openList = TSActivityVO.Factory.build(opList);
		}
		
		return "comListPub";
	}
	
	@SuppressWarnings("unchecked")
	public void subjectsByAct(){
		this.act = this.actBiz.findById(this.id);
		JSONObject result = new JSONObject();
		JSONArray subjects = new JSONArray();
		if(act!=null){
			for (TSSubject sub : act.getSubjects()) {
				subjects.add(sub.getName());
			}
		}
		result.put("subs", subjects);
		Struts2Utils.renderText(result.toJSONString());
	}
	
	/**
	 * 普通教师查看已结束的报名
	 * @author xuechong
	 */
	public String commonFinList(){
		this.pm = this.actBiz.findFinished();
		return "comfinList";
	}
	
	/**
	 * 下载附件啊
	 * @author xuechong
	 * @throws Exception 
	 */
	public void downAtta() throws Exception{
		this.act = this.actBiz.findById(this.id);
		String fileName = act.getFileName();
		String agent = (String) ServletActionContext.getRequest()
		.getHeader("USER-AGENT");
		if (agent != null && agent.indexOf("MSIE") == -1) {
			ServletActionContext.getResponse().setHeader(
					"Content-Disposition",
					"attachment; filename=" +  
					new String(fileName.getBytes("utf-8"),"ISO8859_1"));
		} else {// IE
			ServletActionContext.getResponse().setHeader( 
					"Content-Disposition", 
					"attachment; filename=" +
					URLEncoder.encode(fileName, "UTF8"));
		}
		ServletActionContext.getResponse().
		setContentType("application/vnd.ms-excel;charset=uft-8");
		OutputStream out=null;
		FileInputStream in=null;
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			in = new FileInputStream(new File(act.getFilePath()));
			Utils.StreamUtil.copy(in, out);
			out.flush();
		} catch (Exception e) {
			logger.error(e);
		}finally{
			if(out!=null){out.close();}
		}
	}
	
	private static final String RELOAD_ADMIN = "reloadAdmin";
	////////////////////////
	////getters&setters/////
	////////////////////////
	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public TSActivity getAct() {
		return act;
	}
	public void setAct(TSActivity act) {
		this.act = act;
	}
	public File getAtta() {
		return atta;
	}
	public void setAtta(File atta) {
		this.atta = atta;
	}
	public String getAttaFileName() {
		return attaFileName;
	}
	public void setAttaFileName(String attaFileName) {
		this.attaFileName = attaFileName;
	}
	public Integer getActStatu() {
		return actStatu;
	}
	public void setActStatu(Integer actStatu) {
		this.actStatu = actStatu;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public List<TSActivityVO> getOpenList() {
		return openList;
	}
	public void setOpenList(List<TSActivityVO> openList) {
		this.openList = openList;
	}
	public List<TSActivityVO> getWaitList() {
		return waitList;
	}
	public void setWaitList(List<TSActivityVO> waitList) {
		this.waitList = waitList;
	}
	public List<TSActivity> getFinList() {
		return finList;
	}
	public void setFinList(List<TSActivity> finList) {
		this.finList = finList;
	}
}
