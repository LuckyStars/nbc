package com.nbcedu.function.schoolmaster2.data.impl;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.data.interfaces.DataGenerator;
import com.nbcedu.function.schoolmaster2.data.interfaces.Matcher;

@Matcher(matcher="aaa")
public class TestData implements DataGenerator {

	@Override
	public String getDataByTime(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

}
