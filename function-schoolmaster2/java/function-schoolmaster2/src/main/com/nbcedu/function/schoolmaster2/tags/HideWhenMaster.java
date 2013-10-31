package com.nbcedu.function.schoolmaster2.tags;

/**
 * hide content if curUser is not schoolmaster
 * @author xuechong
 *
 */
@SuppressWarnings("serial")
public class HideWhenMaster extends ShowWheMaster{
	@Override
	protected boolean display() {
		return !super.display();
	}
}
