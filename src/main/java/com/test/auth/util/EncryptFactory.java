package com.test.auth.util;

import com.test.auth.util.IF.EncryptIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptFactory {
	
	@Value("${ENCRYPT}")
	private String type;
	
	@Autowired
	AESEncrypt aesEncrypt; 
	
	public EncryptIF getFactory() {
		if("AES".equals(type)) {
			return aesEncrypt;
		}else {
			return null;
		}
	}
}
