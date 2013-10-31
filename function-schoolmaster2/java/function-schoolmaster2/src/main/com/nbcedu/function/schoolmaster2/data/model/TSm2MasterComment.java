package com.nbcedu.function.schoolmaster2.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nbcedu.function.schoolmaster2.utils.UCService;


public class TSm2MasterComment implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Date createtime;
	private Date lastUpdateTime;
	private String progressId;
	private String content;
	private List<TSm2MasterReply> replys = new ArrayList<TSm2MasterReply>();
	// Constructors

	/** default constructor */
	public TSm2MasterComment() {
	}

	/** full constructor */
	public TSm2MasterComment(String createrId, Date createtime,
			Date lastUpdateTime, String progressId, String content) {
		this.createrId = createrId;
		this.createtime = createtime;
		this.lastUpdateTime = lastUpdateTime;
		this.progressId = progressId;
		this.content = content;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	/**
	 * @return the createrId
	 */
	public String getCreaterName() {
		String name = UCService.findNameByUid(createrId);
		if (name!=null && name.trim().length()>0) {
			return name;
		}
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the replys
	 */
	public List<TSm2MasterReply> getReplys() {
		return replys;
	}

	/**
	 * @param replys the replys to set
	 */
	public void setReplys(List<TSm2MasterReply> replys) {
		this.replys = replys;
	}
}