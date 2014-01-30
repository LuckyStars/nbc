package org.luckystars.weixin.transfer.interfaces;

public interface Validation {
	boolean validate(String timestamp,String nonce,String signature,String token);
}
