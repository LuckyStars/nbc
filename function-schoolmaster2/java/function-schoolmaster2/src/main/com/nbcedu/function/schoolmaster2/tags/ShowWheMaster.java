package com.nbcedu.function.schoolmaster2.tags;

import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.nbcedu.function.schoolmaster2.tags.display.AbstractDisplayTag;
import com.nbcedu.function.schoolmaster2.utils.Utils;

/**
 * display content if curUser is school master
 * @author xuechong
 */
@SuppressWarnings("serial")
public class ShowWheMaster extends AbstractDisplayTag{
	
	@Override
	protected boolean display() {
		for (PersonVo person : Utils.getAllSchoolMaster()) {
			if(person.getUid().equalsIgnoreCase(Utils.curUserUid())){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
}
