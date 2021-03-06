package com.nbcedu.function.cardmanage.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.util.DateUtil;
import com.nbcedu.function.cardmanage.core.util.exl.annotations.ExlData;
import com.nbcedu.function.cardmanage.core.util.exl.annotations.ExlModel;
import com.nbcedu.function.cardmanage.core.util.strings.StringUtil;
import com.nbcedu.function.cardmanage.model.CMCardType;

/**
 * 补卡申请
 * @author xuechong
 */
@SuppressWarnings("serial")
@ExlModel
public class CMApply implements Serializable{
	private String id;
	/**补卡人姓名**/
	@ExlData(sortId=1,title="姓名")
	private String cardUserName;
	/**补卡学生班级**/
	private String cardClassId;
	@ExlData(sortId=2,title="班级")
	private String cardClassName;
	/**申请人姓名*/
	@ExlData(sortId=3,title="申请人")
	private String applyUserName;
	/***申请人uid*/
	private String applyUserUid;
	/**申请时间*/
	private Date createDate;
	private String createDateStr;
	/**申请状态{@link CardStatus}*/
	private Integer status;
	/**补卡类型**/
	private CMCardType cardType = new CMCardType();
	/**补卡说明*/
	private String comment;
	/**补卡类型id**/
	private String cardTypeId;
	/**补卡类型申请日期**/
	private String month;  
	private Date beginDate;
	private Date endDate;
	/**状太**/
	private  CardStatus cardStatus;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//////////////////////////
	//////getters&setters/////
	//////////////////////////
	@ExlData(sortId=4,title="申请时间")
	public String getCreateDateStr(){
		return sdf.format(this.createDate);
	}
	public String getId() {
		return id;
	}
	public CardStatus getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
		if(!StringUtil.isEmpty(month)&&StringUtil.isNumeric(month)){
			this.setEndDate(new Date());
			this.setBeginDate(DateUtil.monthOffset(this.getEndDate(),-Integer.parseInt(month)));
		
		}
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
    public String getCardClassName() {
		return cardClassName;
	}
	public void setCardClassName(String cardClassName) {
		this.cardClassName = cardClassName;
	}
	
	public static void main(String[] args) {
    	CMApply cm = new CMApply();
    	for(CardStatus c:cm.getCardStatus().values()){
    		System.out.println(c.getName());
    	}
    }
    
}
