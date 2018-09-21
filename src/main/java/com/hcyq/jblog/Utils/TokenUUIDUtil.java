package com.hcyq.jblog.Utils;

import java.util.UUID;

public class TokenUUIDUtil {

	
	/**
	 * 获取UUID作为token
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	
}