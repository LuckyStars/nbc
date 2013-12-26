package com.nbcedu.function.schoolmaster2.vo;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.utils.UCService;


public class InvatitionVo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String createrId;
	private Date createTime;
	private String content;
	private String flag;
	private String title;
	private String status = "0";
	private Date lastUpdateTime;
	private String link;
	private Integer score = -1;
	private String name;

	// Constructors

	/** default constructor */
	public InvatitionVo() {
	}

	public InvatitionVo(String createrId) {
		this.createrId=createrId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the createrId
	 */
	public String getCreaterId() {
		return createrId;
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

	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}


//	/**
//	 * @return the invatId
//	 */
//	public String getInvatName() {
//		String name = UCService.findNameByUid(invatId);
//		if (name!=null && name.trim().length()>0) {
//			return name;
//		}
//		return invatId;
//	}


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}