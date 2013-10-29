package com.nbcedu.function.schoolmaster2.tags;

import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.nbcedu.function.schoolmaster2.tags.display.AbstractDisplayTag;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@SuppressWarnings("serial")
public class ShowWhenManager extends AbstractDisplayTag{

	@Override
	protected boolean display() {
		for (PersonVo person : Utils.getAllManager()) {
			if(person.getUid().equalsIgnoreCase(Utils.curUserUid())){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
}
