package com.nbcedu.function.schoolmaster2.utils;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcDiction;

public class UCService {
	private static final BaseClient client = new BaseClient();
	
	public static String findNameByUid(String uid){
		return StringUtils.trimToEmpty(client.queryPerson(1, uid).getName());
	}
	
	public static boolean findIdentity(String uid){
		List<NbcUcDiction> list = client.queryIdentity(uid, 1);
		for(NbcUcDiction n : list){
			System.out.println(n.getTypeName());
//			if(n.g){
//				return true;
//			}
		}
		return false;
	}
}
