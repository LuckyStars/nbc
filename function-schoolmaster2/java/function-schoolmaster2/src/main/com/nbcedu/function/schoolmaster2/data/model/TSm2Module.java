package com.nbcedu.function.schoolmaster2.data.model;

import java.util.Date;


public class TSm2Module implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String discription;
	private Date createtime;
	private String createrid;

	// Constructors

	/** default constructor */
	public TSm2Module() {
	}

	/** full constructor */
	public TSm2Module(String name, String discription, Date createtime,
			String createrid) {
		this.name = name;
		this.discription = discription;
		this.createtime = createtime;
		this.createrid = createrid;
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

	public String getCreaterid() {
		return this.createrid;
	}

	public void setCreaterid(String createrid) {
		this.createrid = createrid;
	}

}