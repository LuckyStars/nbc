package com.nbcedu.function.cardmanage.util;

import com.nbcedu.function.cardmanage.constants.CardStatus;

public class StatusTag extends AbstractStatusDisplayTag<CardStatus>{
	
	private static final long serialVersionUID = -224574221444874882L;

	@Override
	protected CardStatus findById(Integer id) {
		return CardStatus.findById(id);
	}
	
}
