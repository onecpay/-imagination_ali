package com.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * Date: 2014/10/29 Time: 14:18
 * @author rizenguo
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 生成指定长度的数字串
     * @param length 长度
     * @return 字符串数字
     */
    public static String getRandomNumByLength(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 生成指定长度的字符串
     * @param length 长度
     * @return 字符串+数字
     */
    public static String getRandomAlphaNumByLength(int length) {
        String base = "abcdefghijkmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getSerial_number() {
        return DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS") + getRandomNumByLength(6);
    }
}