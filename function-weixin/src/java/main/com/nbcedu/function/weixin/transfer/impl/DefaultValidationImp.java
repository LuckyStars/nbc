package com.nbcedu.function.weixin.transfer.impl;

import javax.annotation.Resource;

import com.nbcedu.function.weixin.transfer.interfaces.Validation;

@Resource(name="WXvali")
public class DefaultValidationImp implements Validation {

	@Override
	public boolean validate(String timestamp, String nonce, String signature,
			String token) {
		// TODO Auto-generated method stub
		return false;
	}

}
