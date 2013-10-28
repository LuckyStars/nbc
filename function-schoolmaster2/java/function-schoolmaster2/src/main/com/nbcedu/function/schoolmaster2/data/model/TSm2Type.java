package com.nbcedu.function.schoolmaster2.data.model;

import java.util.HashSet;
import java.util.Set;


public class TSm2Type implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String moduleId;
	private Integer mold; 
	
	private Set<TSm2TypeUser> typeUsers = new HashSet<TSm2TypeUser>();

	// Constructors

	/** default constructor */
	public TSm2Type() {
	}

	/** full constructor */
	public TSm2Type(String name,String moduleId,Set typeUsers) {
		this.name = name;
		this.moduleId = moduleId;
		this.typeUsers = typeUsers;
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

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Set<TSm2TypeUser> getTypeUsers() {
		return typeUsers;
	}

	public void setTypeUsers(Set<TSm2TypeUser> typeUsers) {
		this.typeUsers = typeUsers;
	}

	public Integer getMold() {
		return mold;
	}

	public void setMold(Integer mold) {
		this.mold = mold;
	}

}