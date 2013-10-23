package com.nbcedu.function.schoolmaster2.invitation.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class SM2InvResource implements Serializable{

	protected String id;
	protected String invId;
	public abstract String displayStr();
	
	/////////////////////
	//getters& setter///
	//////////////////////
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	
}
