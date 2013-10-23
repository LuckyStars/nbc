package com.nbcedu.function.schoolmaster2.invitation.model;


@SuppressWarnings("serial")
public class SM2InvAttachment extends SM2InvResource{

	private String fileName;
	private String path;
	@Override
	public String displayStr() {
		// TODO Auto-generated method stub
		return null;
	}
	///////////////////////
	////getters &setters///
	///////////////////////
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
