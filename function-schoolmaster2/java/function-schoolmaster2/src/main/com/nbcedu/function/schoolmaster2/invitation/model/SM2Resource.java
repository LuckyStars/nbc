package com.nbcedu.function.schoolmaster2.invitation.model;


public abstract class SM2Resource {

	protected String id;
	protected String InvitationId;

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
	public String getInvitationId() {
		return InvitationId;
	}
	public void setInvitationId(String invitationId) {
		InvitationId = invitationId;
	}
	
}
