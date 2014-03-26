package org.luckystars.weixin.transfer.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.luckystars.weixin.transfer.interfaces.Validation;

public class DefaultValidationImp implements Validation {
	
	private static final Logger logger = Logger.getLogger(DefaultValidationImp.class);

	@Override
	public boolean validate(String timestamp, String nonce, String signature,
			String token) {
		if(isBlank(timestamp)||isBlank(nonce)||isBlank(signature)||isBlank(token)){
			return false;
		}
		
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
			logger.error(e);
			System.exit(0);///something is really wrong here... no need to continue 
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

	private boolean isBlank(String str){
		return str==null||str.trim().isEmpty();
	}
}
