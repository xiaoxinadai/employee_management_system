package com.situ.utils;

public class StringUtils {
    /*
     * 判断一个字符串对象是否为null或空字符串
     * 
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
