package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/** @author admin @ClassName: Disguiser @Description: 哈希算法的工具类，提供SHA MD5 HMAC等算法 */
public class Disguiser {

  public static final String ENCODE = "UTF-8";
  private static final String KEY = "8data998mnwepxugnk03-2zirb";

  public static void main(String[] args) {
    System.out.println(21);
    System.out.println(
            disguiseMD5(
                    "#OnlineQuery#0000#KY0003484733#ZHRBw950F4h6w0kZ1h0#0.01#WX#WX#2018-07-10 17:15:05#2018-07-10 17:15:14#SUCCESS#INIT#l9UvFB0OS5iFOUjNezHD2JPAw5FjkjHk"));
  }

  public static String disguise(String message) {
    return disguise(message + KEY, ENCODE);
  }

  public static String disguise(String message, String encoding) {
    message = message.trim();
    byte[] value;
    try {
      value = message.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      value = message.getBytes();
    }
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    return BaseConvertUtils.toHex(md.digest(value));
  }

  public static String disguiseMD5(String message) {

    if (null == message) {

      return null;
    }

    return disguiseMD5(message, ENCODE);
  }

  public static String disguiseMD5(String message, String encoding) {

    if (null == message || null == encoding) {

      return null;
    }

    message = message.trim();
    byte[] value;
    try {
      value = message.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      value = message.getBytes();
    }
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    return BaseConvertUtils.toHex(md.digest(value));
  }

  public static String string2MD5(String inStr) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
      return "";
    }
    char[] charArray = inStr.toCharArray();
    byte[] byteArray = new byte[charArray.length];

    for (int i = 0; i < charArray.length; i++) {
      byteArray[i] = (byte) charArray[i];
    }
    byte[] md5Bytes = md5.digest(byteArray);
    StringBuffer hexValue = new StringBuffer();
    for (int i = 0; i < md5Bytes.length; i++) {
      int val = ((int) md5Bytes[i]) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();
  }

  /**
   * 对报文进行采用MD5进行hmac签名
   *
   * @param aValue - 字符串
   * @param aKey - 密钥
   * @param encoding - 字符串编码方式
   * @return - 签名结果，hex字符串
   */
  public static String hmacSign(String aValue, String aKey, String encoding) {
    byte[] kIpad = new byte[64];
    byte[] kOpad = new byte[64];
    byte[] keyb;
    byte[] value;
    try {
      keyb = aKey.getBytes(encoding);
      value = aValue.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      keyb = aKey.getBytes();
      value = aValue.getBytes();
    }
    Arrays.fill(kIpad, keyb.length, 64, (byte) 54);
    Arrays.fill(kOpad, keyb.length, 64, (byte) 92);
    for (int i = 0; i < keyb.length; i++) {
      kIpad[i] = (byte) (keyb[i] ^ 0x36);
      kOpad[i] = (byte) (keyb[i] ^ 0x5c);
    }
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    md.update(kIpad);
    md.update(value);
    byte[] dg = md.digest();
    md.reset();
    md.update(kOpad);
    md.update(dg, 0, 16);
    dg = md.digest();
    return BaseConvertUtils.toHex(dg);
  }

  /**
   * 对报文进行hmac签名，字符串按照UTF-8编码
   *
   * @param aValue - 字符串
   * @param aKey - 密钥
   * @return - 签名结果，hex字符串
   */
  public static String hmacSign(String aValue, String aKey) {
    return hmacSign(aValue, aKey, ENCODE);
  }
}
