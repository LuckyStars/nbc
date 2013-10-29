package com.nbcedu.function.schoolmaster2.tags;

@SuppressWarnings("serial")
public class HideWhenManager extends ShowWhenManager{
	@Override
	protected boolean display() {
		return !super.display();
	}
}
