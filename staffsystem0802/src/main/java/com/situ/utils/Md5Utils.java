package com.situ.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Md5操作工具类
 */
public class Md5Utils {
	/**
	 * 对字符串进行加密处理
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
	}
}
