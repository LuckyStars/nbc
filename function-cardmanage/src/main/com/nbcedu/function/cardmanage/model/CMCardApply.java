package com.nbcedu.function.cardmanage.model;

import java.io.Serializable;
import java.util.Date;
import com.nbcedu.function.cardmanage.constants.CardStatus;

/**
 * 补卡申请
 * @author xuechong
 */
@SuppressWarnings("serial")
public class CMCardApply implements Serializable{
	private String id;
	/**补卡人姓名**/
	private String cardUserName;
	/**补卡学生班级**/
	private String cardClassId;
	/**申请人姓名*/
	private String applyUserName;
	/***申请人uid*/
	private String applyUserUid;
	/**申请时间*/
	private Date createDate;
	/**申请状态{@link CardStatus}*/
	private Integer status;
	/**补卡类型**/
	private CMCardType cardType;
	/**补卡说明*/
	private String comment;
	//////////////////////////
	//////getters&setters/////
	//////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**补卡人姓名**/
	public String getCardUserName() {
		return cardUserName;
	}
	/**补卡人姓名**/
	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}
	/**补卡学生班级**/
	public String getCardClassId() {
		return cardClassId;
	}
	/**补卡学生班级**/
	public void setCardClassId(String cardClassId) {
		this.cardClassId = cardClassId;
	}
	/**申请人姓名*/
	public String getApplyUserName() {
		return applyUserName;
	}
	/**申请人姓名*/
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	/***申请人uid*/
	public String getApplyUserUid() {
		return applyUserUid;
	}
	/***申请人uid*/
	public void setApplyUserUid(String applyUserUid) {
		this.applyUserUid = applyUserUid;
	}
	/**申请时间*/
	public Date getCreateDate() {
		return createDate;
	}
	/**申请时间*/
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**申请状态{@link CardStatus}*/
	public Integer getStatus() {
		return status;
	}
	/**申请状态{@link CardStatus}*/
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**补卡说明*/
	public String getComment() {
		return comment;
	}
	/**补卡说明*/
	public void setComment(String comment) {
		this.comment = comment;
	}
	public CMCardType getCardType() {
		return cardType;
	}
	public void setCardType(CMCardType cardType) {
		this.cardType = cardType;
	}
	
}
