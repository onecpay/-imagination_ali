package com.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 数据判断工具 提供基础的数据判断方法 @Author Zale
 *
 * @date 2016/11/28 下午7:21
 */
public class ValidateUtils {
  /** 正则表达式：验证手机号 */
  public static final String REGEX_MOBILE = "^((1[3-9][0-9]))\\d{8}$";
  /** 正则表达式：验证邮箱 */
  public static final String REGEX_EMAIL =
      "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
  /** 正则表达式：验证URL */
  public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

  /** 正则表达式：验证IP地址 */
  public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
  /** 正则表达式：验证身份证 */
  public static final String REGEX_ID_CARD = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
  /** 正则表达式：验证汉字 */
  public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
  /** 正则表达式：数字 */
  public static final String REGEX_NUMERIC = "([1-9]+[0-9]*|0)(\\.[\\d]+)?";
  /** 正则表达式：小数 */
  public static final String REGEX_DECIMAL = "([1-9]+[0-9]*|0)(\\.[\\d]+)";
  /** 正则表达式：数字 */
  public static final String REGEX_INTEGER = "[0-9]*";

  /**
   * 判断字符串是否为空
   *
   * @param str 字符串数据
   * @return 判断结果
   */
  public static boolean isStrEmpty(String str) {
    return null == str || 0 == str.trim().length();
  }

  /**
   * 判断Collection集合是否为空
   *
   * @param collection 集合数据
   * @return 判断结果
   */
  public static boolean isCollectionEmpty(Collection<?> collection) {
    return null == collection || 0 == collection.size();
  }

  /**
   * 判断Map是否为空
   *
   * @param map map数据
   * @return 判断结果
   */
  public static boolean isMapEmpty(Map<?, ?> map) {
    return null == map || 0 == map.size();
  }

  /**
   * 判断数组是否为空
   *
   * @param arr object数组
   * @return 判断结果
   */
  public static boolean isArrEmpty(Object[] arr) {
    return null == arr || 0 == arr.length;
  }

  /**
   * 判断byte数组是否为空
   *
   * @param arr byte数组
   * @return 判断结果
   */
  public static boolean isByteArrEmpty(byte[] arr) {
    return null == arr || 0 == arr.length;
  }

  /**
   * 判断是否是身份证
   *
   * @param idCard 身份证字符串
   */
  public static boolean isIdCard(String idCard) {
    return Pattern.matches(REGEX_ID_CARD, idCard);
  }

  /**
   * 判断是否为手机号
   *
   * @param mobile 手机号
   * @return 验证结果
   */
  public static boolean isMobile(String mobile) {
    return Pattern.matches(REGEX_MOBILE, mobile);
  }

  /**
   * 校验身份证
   *
   * @param idCard
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isIDCard(String idCard) {
    return Pattern.matches(REGEX_ID_CARD, idCard);
  }

  /**
   * 校验邮箱
   *
   * @param email
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isEmail(String email) {
    return Pattern.matches(REGEX_EMAIL, email);
  }

  /**
   * 校验URL
   *
   * @param url
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUrl(String url) {
    return Pattern.matches(REGEX_URL, url);
  }

  /**
   * 校验IP地址
   *
   * @param ipAddr
   * @return
   */
  public static boolean isIPAddr(String ipAddr) {
    return Pattern.matches(REGEX_IP_ADDR, ipAddr);
  }

  /**
   * 判断字符串是否为整数
   *
   * @param numberStr 字符串
   * @return 验证结果
   */
  public static boolean isInteger(String numberStr) {
    return Pattern.matches(REGEX_INTEGER, numberStr);
  }

  /**
   * 判断字符串是否为数字
   *
   * @param numberStr 字符串
   * @return 验证结果
   */
  public static boolean isNumeric(String numberStr) {
    return Pattern.matches(REGEX_NUMERIC, numberStr);
  }

  /**
   * 判断是否是数字，包含小数
   *
   * @param decimal 字符串
   * @return 验证结果
   */
  public static boolean isDecimal(String decimal) {
    return Pattern.matches(REGEX_DECIMAL, decimal);
  }

  /**
   * 判断是否是汉字
   *
   * @param chinese 文字字符串
   * @return 验证结果
   */
  public static boolean isChinese(String chinese) {
    return Pattern.matches(REGEX_CHINESE, chinese);
  }
}
