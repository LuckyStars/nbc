package com.nbcedu.function.cardmanage.model;

import java.io.Serializable;
/**
 * 补卡申请
 * @author xuechong
 */
@SuppressWarnings("serial")
public class CMCardUser implements Serializable{
	private String id;
	/**补卡人姓名**/
	private String cardUserName;
	/**补卡学生班级**/
	private String cardUserId;
	//////////////////////////
	//////getters&setters/////
	//////////////////////////
	
	public String getId() {
		return id;
	}

	public String getCardUserName() {
		return cardUserName;
	}

	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}

	public String getCardUserId() {
		return cardUserId;
	}

	public void setCardUserId(String cardUserId) {
		this.cardUserId = cardUserId;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
