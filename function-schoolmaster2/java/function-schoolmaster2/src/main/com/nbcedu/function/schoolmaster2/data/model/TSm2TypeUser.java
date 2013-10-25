package com.nbcedu.function.schoolmaster2.data.model;

public class TSm2TypeUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String typeId;
	private String userId;

	// Constructors

	/** default constructor */
	public TSm2TypeUser() {
	}

	/** full constructor */
	public TSm2TypeUser(String typeId, String userId) {
		this.typeId = typeId;
		this.userId = userId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}