package com.nbcedu.function.teachersignup.core.util;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

/**
 * ResultTransformer适配器，可扩展实现自定义类
 * 
 * @version 1.0
 */
public abstract class BasicTransformerAdapter implements ResultTransformer {
	private static final long serialVersionUID = 1L;
	
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple;
	}

	@SuppressWarnings("unchecked")
	public List transformList(List list) {
		return list;
	}
}
