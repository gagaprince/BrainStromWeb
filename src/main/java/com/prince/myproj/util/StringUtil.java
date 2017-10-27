package com.prince.myproj.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class StringUtil {
	public static String getStringUTF8(String instr){
		String outStr = "";
		try {
			outStr = new String(instr.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outStr;
	}
	
	public static String getStringISO88591(String instr){
		String outStr = "";
		try {
			outStr = new String(instr.getBytes("utf-8"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outStr;
	}

	public static int parseIntFromRequest(HttpServletRequest request,String key,int defaultVal){
		String valStr = request.getParameter(key);
		int val = defaultVal;
		if(valStr!=null){
			val = Integer.parseInt(valStr);
		}
		return val;
	}

	public static long parseLongFromRequest(HttpServletRequest request,String key,long defaultVal){
		String valStr = request.getParameter(key);
		long val = defaultVal;
		if(valStr!=null){
			val = Long.parseLong(valStr);
		}
		return val;
	}

	public static String parseStringFromRequest(HttpServletRequest request,String key,String defaultVal){
		String valStr = request.getParameter(key);
		String val = defaultVal;
		if(valStr!=null&&!valStr.equals("")){
			try {
				val = new String(valStr.getBytes("ISO8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	public static String getString(String reVal,String deVal){
		if(reVal==null){
			reVal = deVal;
		}
		return reVal;
	}

	public static String convertUnicodeToUTF(String unicode){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while((i=unicode.indexOf("%u", pos)) != -1){
			sb.append(unicode.substring(pos, i));
			try{
				if(i+5 < unicode.length()){
					pos = i+6;
					sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
				}
			}catch (Exception e){
				// 说明不是unicode字符
				sb.append(unicode.substring(i+1, i+6));
			}
		}
		if (pos < unicode.length()) {
			sb.append(unicode.substring(pos, unicode.length()));
		}
		return sb.toString();
	}
}
