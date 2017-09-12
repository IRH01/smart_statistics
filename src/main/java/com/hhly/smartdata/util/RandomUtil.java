package com.hhly.smartdata.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static String[] chars = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static Random random = new Random();

    public static String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(getRandomString());
        }
        return builder.toString();
    }

    private static String getRandomString() {
        int i = random.nextInt(chars.length - 1);
        return chars[i];
    }
    
    /**  
     * 生成32位编码  
     * @return string  
     */    
    public static String getUUID(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    } 

    public static void main(String[] args) {
        System.out.println(getRandomString(12));
    }
}
