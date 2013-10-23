package com.nbcedu.function.schoolmaster2.data.model;

public class TSm2SubjectUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String subjectId;
	private String userId;

	// Constructors

	/** default constructor */
	public TSm2SubjectUser() {
	}

	/** full constructor */
	public TSm2SubjectUser(String subjectId, String userId) {
		this.subjectId = subjectId;
		this.userId = userId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}