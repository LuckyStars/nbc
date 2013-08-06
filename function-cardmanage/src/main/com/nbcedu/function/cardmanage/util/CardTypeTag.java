package com.nbcedu.function.cardmanage.util;

import com.nbcedu.function.cardmanage.constants.CardType;

public class CardTypeTag extends AbstractStatusDisplayTag<CardType>{
	@Override
	protected CardType findById(Integer id) {
		return CardType.findById(id);
	}
}
