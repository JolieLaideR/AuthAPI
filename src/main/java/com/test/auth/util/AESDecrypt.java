package com.test.auth.util;


import com.test.auth.util.IF.DecryptIF;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


@Component
public class AESDecrypt implements DecryptIF {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${SECRETKEY}")
	private String key;
	
	String dec = null;
	
	public String decode(String value) {
		try {
			if(key.length() > 32) {
				key.substring(0, 32);
			}else if(key.length() < 32) {
				key = StringUtils.rightPad(key, 32, "0");
			}
			String  iv = key.substring(0,16);
			byte[] keyBytes = new byte[16];
			byte[] keyData = key.getBytes(StandardCharsets.UTF_8);
			int len = keyData.length;
			
			if(len > keyBytes.length) {
				len = keyBytes.length;
			}
			
			System.arraycopy(keyData, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
	 
	        byte[] byteStr = Base64.decodeBase64(value.getBytes());
	 
	        dec = new String(c.doFinal(byteStr), StandardCharsets.UTF_8);

		}catch(Exception e) {
			logger.error(e.getMessage());
			for (StackTraceElement st : e.getStackTrace()) {
				if (st.toString().startsWith("com.tigen")) {
					logger.error(st.toString());
				}
			}
		}
		
		return dec;
	}

}
