package com.gcgProject.util;

import java.util.UUID;

/**
 *
 * @author lion
 * @date 2016-9-1
 */
public class IDUtils {
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
