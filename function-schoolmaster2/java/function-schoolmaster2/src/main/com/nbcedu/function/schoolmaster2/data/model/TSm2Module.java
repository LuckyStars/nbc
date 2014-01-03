package com.nbcedu.function.schoolmaster2.data.model;

import java.util.Date;


public class TSm2Module implements java.io.Serializable {
	/***有关联重心工作***/
	public static final int FLAG_YOUGUANLIAN = 3;
	/***主管显示***/
	public static final int FLAG_ZHUGUAN_XIANSHI = 3;
	
	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String discription;
	private Date createtime;
	private String createrId;
	private String parentId;
	private String icon;
//	为1：主管显示   3:有关联重心工作
	private int flag;

	// Constructors

	/** default constructor */
	public TSm2Module() {
	}

	/** full constructor */
	public TSm2Module(String name, String discription, Date createtime,
			String createrId,int flag ) {
		this.name = name;
		this.discription = discription;
		this.createtime = createtime;
		this.createrId = createrId;
		this.flag = flag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return this.discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrid) {
		this.createrId = createrid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}