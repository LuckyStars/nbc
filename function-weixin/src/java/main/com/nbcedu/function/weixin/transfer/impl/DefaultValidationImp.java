package com.nbcedu.function.weixin.transfer.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.nbcedu.function.weixin.transfer.interfaces.Validation;

@Resource(name="WXvali")
public class DefaultValidationImp implements Validation {

	@Override
	public boolean validate(String timestamp, String nonce, String signature,
			String token) {
		List<String> list = new ArrayList<String>();
		
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		
		Collections.sort(list);
		
		StringBuilder joinstr = new StringBuilder();
		for (String string : list) {
			joinstr.append(string);
		}
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(joinstr.toString().getBytes());
		byte[] digest = md.digest();
		
		StringBuilder sha = new StringBuilder();
		for (byte b : digest) {
			String hexValue = Integer.toHexString(b >= 0 ? b : 256 + b);
			if(hexValue.length()<2){
				hexValue = "0" + hexValue;
			}
			sha.append(hexValue);
		}
		
		return signature.equals(sha.toString());
	}

}
