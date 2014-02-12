package org.luckystars.weixin.transfer.interfaces;

/**
 * 接入验证
 * @author xuechong
 */
public interface Validation {
	boolean validate(String timestamp,String nonce,String signature,String token);
}
 