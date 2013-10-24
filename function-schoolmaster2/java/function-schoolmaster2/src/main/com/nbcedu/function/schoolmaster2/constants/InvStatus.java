package com.nbcedu.function.schoolmaster2.constants;

import com.nbcedu.function.schoolmaster2.tags.status.StatusEnum;

public enum InvStatus implements StatusEnum{
	
	;

	private int id;
	private String name;
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public int getId() {
		return this.id;
	}
	public static InvStatus findById(Integer id) {
		return null;
	}
}
