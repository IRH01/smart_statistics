package com.hhly.smartdata.util;

public class PasswordUtil {
    private static final String PREFIX = "43Yhds:><";
    private static final String SUFFIX = "k%$sdj698";

    public static String decode(String sourcePassword) {
        return Md5Util.getMD5((PREFIX + sourcePassword + SUFFIX).getBytes());
    }

    public static void main(String[] args){
        System.out.println(PasswordUtil.decode("123456"));
    }
}
