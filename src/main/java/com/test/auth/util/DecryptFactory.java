package com.test.auth.util;

import com.test.auth.util.IF.DecryptIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DecryptFactory {
	@Value("${DECRYPT}")
	private String type;
	
	@Autowired
	AESDecrypt aesDecrypt; 
	
	public DecryptIF getFactory() {
		if("AES".equals(type)) {
			return aesDecrypt;
		}else {
			return null;
		}
	}
}
