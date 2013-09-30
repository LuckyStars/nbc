package com.nbcedu.function.schoolmaster2.utils;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.integration.uc.client.facade.BaseClient;

public class UCService {
	private static final BaseClient client = new BaseClient();
	
	public static String findNameByUid(String uid){
		return StringUtils.trimToEmpty(client.queryPerson(1, uid).getName());
	}
}
