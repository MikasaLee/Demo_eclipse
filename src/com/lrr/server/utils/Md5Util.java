package com.lrr.server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	/**
	 * �������
	 * 
	 * @param newpass
	 * @return
	 *
	 */
	public static String md5Encryption(String input) {
		StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] result = digester.digest(input.getBytes());
			for (byte b : result) {
				int number = b & 0xff;// ���� + 1
				String hexStr = Integer.toHexString(number);
				if (hexStr.length() == 1) {
					buffer.append("0");
				}
				buffer.append(hexStr);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
