package com.nbcedu.function.schoolmaster2.core.comment;


import java.util.List;

import org.hibernate.transform.ResultTransformer;

/**
 * Provides the basic "noop" impls of the {@link ResultTransformer} contract.
 *
 * @author Steve Ebersole
 */
@SuppressWarnings("serial")
public abstract class BasicTransformerAdapter implements ResultTransformer {
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple;
	}

	@SuppressWarnings("unchecked")
	public List transformList(List list) {
		return list;
	}
}
