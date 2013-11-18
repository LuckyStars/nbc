package com.nbcedu.function.schoolmaster2.vo;

public class SubjectZanVo {
	private final String id;
	private final String creatorId;
	private final String title;
	
	/**
	 * @param id
	 * @param creatorId
	 * @param title
	 */
	public SubjectZanVo(String id,String creatorId, String title) {
		super();
		this.id = id;
		this.creatorId = creatorId;
		this.title = title;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public String getTitle() {
		return title;
	}
	public String getId() {
		return id;
	}
}
