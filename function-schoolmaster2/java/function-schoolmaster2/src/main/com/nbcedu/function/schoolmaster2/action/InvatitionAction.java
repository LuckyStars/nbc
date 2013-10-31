package com.nbcedu.function.schoolmaster2.action;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterCommentBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterReplyBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterComment;
import com.nbcedu.function.schoolmaster2.data.model.TSm2MasterReply;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Resource;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

@SuppressWarnings("serial")
public class InvatitionAction extends BaseAction{

	private TSm2Invatition tsm2Invatition;
	
	private TSm2Subject subject;
	private SubjectVo subjectVo = new SubjectVo();

	private SM2InvatitionBiz sm2InvatitionBiz;
	
	private SM2ResourceBiz sm2ResourceBiz;
	
	private SM2MasterCommentBiz sm2MasterCommentBiz;
	
	private SM2MasterReplyBiz sm2MasterReplyBiz;
	
	private String[] resourses;
	
	private String[] delResourses;
	
	private String fileName;
	
	private String filePath;
	
	private List<TSm2Resource> tsm2Resources;
	
	private List<TSm2MasterComment> tsm2MasterComments;

	private Integer score;


	public String add(){
		
		Date date = new Date();
		tsm2Invatition.setCreaterId(this.getUserId());
		tsm2Invatition.setCreateTime(date);
		sm2InvatitionBiz.add(tsm2Invatition);
		if ("0".equals(tsm2Invatition.getFlag())) {
			for (String resourse : resourses) {
				TSm2Resource tr = new TSm2Resource();
				tr.setCreaterId(this.getUserId());
				tr.setCreateTime(date);
				tr.setFileName(resourse.substring(resourse.lastIndexOf(File.separatorChar)+1));
				tr.setFilePath(resourse);
				tr.setProgressId(tsm2Invatition.getId());
				sm2ResourceBiz.add(tr);
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String modify() throws IllegalAccessException, InvocationTargetException{
		
		TSm2Invatition tsm = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		Date date = new Date();
		if ("0".equals(tsm.getFlag())&&"1".equals(tsm2Invatition.getFlag())) {
			List<TSm2Resource> tsr = sm2ResourceBiz.findByInvatitionId(tsm2Invatition.getId());
			for (TSm2Resource tSm2Resource : tsr) {
				sm2ResourceBiz.remove(tSm2Resource);
			}
		}else if (("1".equals(tsm.getFlag())&&"0".equals(tsm2Invatition.getFlag()))) {
			for (String resourse : resourses) {
				TSm2Resource tr = new TSm2Resource();
				tr.setCreaterId(this.getUserId());
				tr.setCreateTime(date);
				tr.setFileName(resourse.substring(resourse.lastIndexOf(File.separatorChar)+1));
				tr.setFilePath(resourse);
				tr.setProgressId(tsm2Invatition.getId());
				sm2ResourceBiz.add(tr);
			}
		}else if ("0".equals(tsm.getFlag())&&"0".equals(tsm2Invatition.getFlag())) {
			if (delResourses!=null&&delResourses.length>0) {
				for (String delResourse : delResourses) {
					sm2ResourceBiz.removeById(delResourse);
				}
			}
			if (resourses!=null&&resourses.length>0) {
				for (String resourse : resourses) {
					TSm2Resource tr = new TSm2Resource();
					tr.setCreaterId(this.getUserId());
					tr.setCreateTime(date);
					tr.setFileName(resourse.substring(resourse.lastIndexOf(File.separatorChar)+1));
					tr.setFilePath(resourse);
					tr.setProgressId(tsm2Invatition.getId());
					sm2ResourceBiz.add(tr);
				}
			}
		}
		tsm.setLastUpdateTime(date);
		tsm.setContent(tsm2Invatition.getContent());
		tsm.setFlag(tsm2Invatition.getFlag());
		tsm.setInvatId(tsm2Invatition.getInvatId());
		tsm.setLink(tsm2Invatition.getLink());
		tsm.setTitle(tsm2Invatition.getTitle());
		sm2InvatitionBiz.modify(tsm);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String modifyScore() throws IllegalAccessException, InvocationTargetException{
		TSm2Invatition tsm = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		Date date = new Date();
		tsm.setLastUpdateTime(date);
		tsm.setScore(score);
		sm2InvatitionBiz.modify(tsm);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String teacherList(){
		this.pm = sm2InvatitionBiz.findByCreaterId(this.getUserId());
		return "teacherList";
	}
	public String masterList(){
        this.pm = sm2InvatitionBiz.findByInvatId(this.getUserId());
		return "masterList";
	}
	public String push(){
		tsm2Invatition = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		tsm2Invatition.setStatus("1");
		sm2InvatitionBiz.modify(tsm2Invatition);
		return this.teacherList();
	}
	public String comment(){
		TSm2MasterComment comment = new TSm2MasterComment();
		comment.setContent(fileName);
		comment.setCreaterId(this.getUserId());
		comment.setCreatetime(new Date());
		comment.setProgressId(tsm2Invatition.getId());
		sm2MasterCommentBiz.add(comment);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String reply(){
		TSm2MasterReply reply = new TSm2MasterReply();
		reply.setContent(fileName);
		reply.setCreaterId(this.getUserId());
		reply.setCreatetime(new Date());
		reply.setProgressId(tsm2Invatition.getId());
		sm2MasterReplyBiz.add(reply);
		JSONObject jo = new JSONObject();
		jo.put("error", "OK");
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String del(){
		sm2InvatitionBiz.removeById(tsm2Invatition.getId());
		return this.teacherList();
	}
	public String teacherShow(){
		tsm2Invatition = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		tsm2MasterComments = sm2MasterCommentBiz.findByInvatitionId(tsm2Invatition.getId());
		for (TSm2MasterComment tsm: tsm2MasterComments) {
			List<TSm2MasterReply> rpList = sm2MasterReplyBiz.findByCommentId(tsm.getId());
			tsm.setReplys(rpList);
		}
		if("0".equals(tsm2Invatition.getFlag())){
			tsm2Resources = sm2ResourceBiz.findByInvatitionId(tsm2Invatition.getId());
		}
		return "teacherShow";
	}
	public String masterShow(){
		tsm2Invatition = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		tsm2MasterComments = sm2MasterCommentBiz.findByInvatitionId(tsm2Invatition.getId());
		for (TSm2MasterComment tsm: tsm2MasterComments) {
			List<TSm2MasterReply> rpList = sm2MasterReplyBiz.findByCommentId(tsm.getId());
			tsm.setReplys(rpList);
		}
		if("0".equals(tsm2Invatition.getFlag())){
			tsm2Resources = sm2ResourceBiz.findByInvatitionId(tsm2Invatition.getId());
		}
		return "masterShow";
	}
	public String detail(){
		JSONObject jo = new JSONObject();
		tsm2Invatition = sm2InvatitionBiz.findById(tsm2Invatition.getId());
		jo.put("name", tsm2Invatition.getTitle());
		jo.put("invatId", tsm2Invatition.getInvatId());
		jo.put("content", tsm2Invatition.getContent());
		jo.put("flag", tsm2Invatition.getFlag());
		Gson gson = new Gson();
		if("0".equals(tsm2Invatition.getFlag())){
			List<TSm2Resource> tsr = sm2ResourceBiz.findByInvatitionId(tsm2Invatition.getId());
			jo.put("resources", gson.toJson(tsr, new TypeToken<List<TSm2Resource>>(){}.getType()));
		}else {
			jo.put("link", tsm2Invatition.getLink());
		}
		Struts2Utils.renderJson(jo.toJSONString());
		return null;
	}
	public String download() {
		TSm2Resource cware= sm2ResourceBiz.findById(tsm2Invatition.getId());
		fileName = cware.getFileName();
		filePath = cware.getFilePath();
		return "download";
	}

	/**
	 * @return the tsm2Invatition
	 */
	public TSm2Invatition getTsm2Invatition() {
		return tsm2Invatition;
	}

	/**
	 * @param tsm2Invatition the tsm2Invatition to set
	 */
	public void setTsm2Invatition(TSm2Invatition tsm2Invatition) {
		this.tsm2Invatition = tsm2Invatition;
	}

	/**
	 * @param sm2InvatitionBiz the sm2InvatitionBiz to set
	 */
	public void setSm2InvatitionBiz(SM2InvatitionBiz sm2InvatitionBiz) {
		this.sm2InvatitionBiz = sm2InvatitionBiz;
	}

	/**
	 * @return the resourses
	 */
	public String[] getResourses() {
		return resourses;
	}

	/**
	 * @param resourses the resourses to set
	 */
	public void setResourses(String[] resourses) {
		this.resourses = resourses;
	}

	/**
	 * @param sm2ResourceBiz the sm2ResourceBiz to set
	 */
	public void setSm2ResourceBiz(SM2ResourceBiz sm2ResourceBiz) {
		this.sm2ResourceBiz = sm2ResourceBiz;
	}
	/**
	 * @return the delResourses
	 */
	public String[] getDelResourses() {
		return delResourses;
	}
	/**
	 * @param delResourses the delResourses to set
	 */
	public void setDelResourses(String[] delResourses) {
		this.delResourses = delResourses;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the tsm2Resources
	 */
	public List<TSm2Resource> getTsm2Resources() {
		return tsm2Resources;
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * @param sm2MasterCommentBiz the sm2MasterCommentBiz to set
	 */
	public void setSm2MasterCommentBiz(SM2MasterCommentBiz sm2MasterCommentBiz) {
		this.sm2MasterCommentBiz = sm2MasterCommentBiz;
	}
	/**
	 * @param sm2MasterReplyBiz the sm2MasterReplyBiz to set
	 */
	public void setSm2MasterReplyBiz(SM2MasterReplyBiz sm2MasterReplyBiz) {
		this.sm2MasterReplyBiz = sm2MasterReplyBiz;
	}
	/**
	 * @return the tsm2MasterComments
	 */
	public List<TSm2MasterComment> getTsm2MasterComments() {
		return tsm2MasterComments;
	}

	public SubjectVo getSubjectVo() {
		return subjectVo;
	}

	public void setSubjectVo(SubjectVo subjectVo) {
		this.subjectVo = subjectVo;
	}
	
}
