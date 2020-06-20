package com.lrr.server.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符串编码工具类
 * 
 * @author zhangYB
 *
 */
public class EncodeUtil {
	public static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
