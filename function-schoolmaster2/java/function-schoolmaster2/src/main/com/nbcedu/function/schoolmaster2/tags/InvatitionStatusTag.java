package com.nbcedu.function.schoolmaster2.tags;

import com.nbcedu.function.schoolmaster2.constants.InvStatus;
import com.nbcedu.function.schoolmaster2.tags.status.AbstractStatusDisplayTag;

public class InvatitionStatusTag extends AbstractStatusDisplayTag<InvStatus>{

	@Override
	protected InvStatus findById(Integer id) {
		
		return InvStatus.findById(id);
	}

}
