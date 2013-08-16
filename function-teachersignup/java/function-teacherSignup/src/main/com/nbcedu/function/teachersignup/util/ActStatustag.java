package com.nbcedu.function.teachersignup.util;

import com.nbcedu.function.teachersignup.constants.ActStatus;

@SuppressWarnings("serial")
public class ActStatustag extends AbstractStatusDisplayTag<ActStatus>{

	@Override
	protected ActStatus findById(Integer id) {
		return ActStatus.findById(id);
	}

}
