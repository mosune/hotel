package com.gcgProject.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	
	public static boolean isNotEmpty(String str){
		if(str != null && !"".equals(str)){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str) || "null".equals(str)){
			return true;
		}
		return false;
	}
	
	public static String fmtDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String parseArrayToStr(String[] ar) {
		if(ar == null || ar.length == 0) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
