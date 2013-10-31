package com.nbcedu.function.schoolmaster2.constants;

import com.nbcedu.function.schoolmaster2.tags.status.StatusEnum;

public enum InvStatus implements StatusEnum{
	UNPUB(0,"未发布"),PUBLISHED(1,"已发布"),UNKONOW(-1,"未知");

	private int id;
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	private InvStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public int getId() {
		return this.id;
	}
	public static InvStatus findById(Integer id) {
		switch (id) {
		case 0:
			return UNPUB;
		case 1:
			return PUBLISHED;
		default:
			return UNKONOW;
		}
	}
}
