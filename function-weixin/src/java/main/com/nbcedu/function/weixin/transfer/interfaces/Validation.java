package com.nbcedu.function.weixin.transfer.interfaces;

public interface Validation {
	boolean validate(String timestamp,String nonce,String signature,String token);
}
