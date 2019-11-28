package com.common.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: card
 * @Date: 2019/5/29 15:05
 * @Author: Hebing
 * @Description:
 */
public class DesensitizationUtil {

  /**
   * 加密
   *
   * @param content  需要加密的内容
   * @param password 加密密码
   * @return
   */
  public static String encrypt(String content, String password) {
    try {
      byte[] aesKey = password.getBytes();
      SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
      // 创建密码器
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      byte[] byteContent = content.getBytes("utf-8");
      IvParameterSpec iv = new IvParameterSpec(aesKey, 0, cipher.getBlockSize());
      cipher.init(Cipher.ENCRYPT_MODE, key, iv);
      return encryptBase64(cipher.doFinal(byteContent))
          .replaceAll("[\\s*\t\n\r]", "");
    } catch (Throwable th) {
      th.printStackTrace();
      return content;
    }
  }

  /**
   * 解密
   *
   * @param content  待解密内容
   * @param password 解密密钥
   * @return
   */
  public static String decrypt(String content, String password) {
    try {
      byte[] aesKey = password.getBytes();
      SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
      // 创建密码器
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      IvParameterSpec iv = new IvParameterSpec(aesKey, 0, cipher.getBlockSize());
      cipher.init(Cipher.DECRYPT_MODE, key, iv);
      return new String(cipher.doFinal(decryptBase64(content)));
    } catch (Throwable th) {
      th.printStackTrace();
      return content;
    }
  }


  /**
   * BASE64解密
   *
   * @param key
   * @return
   * @throws Exception
   */
  private static byte[] decryptBase64(String key) throws Exception {
    return (new BASE64Decoder()).decodeBuffer(key);
  }

  /**
   * BASE64 加密
   *
   * @param key
   * @return
   * @throws Exception
   */
  private static String encryptBase64(byte[] key) throws Exception {
    return (new BASE64Encoder()).encodeBuffer(key);
  }

  private static Boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  public static String maskBankCard(String bankCardNo) {
    return isEmpty(bankCardNo) ? bankCardNo : String.format("**** **** **** %s", bankCardNo.substring(bankCardNo.length() - 4));
  }

  public static String maskBankCard(String bankCardNo, Integer beginIndex, Integer endIndex) {
    if (!isEmpty(bankCardNo) && bankCardNo.length() < beginIndex) {
      return bankCardNo;
    }
    return isEmpty(bankCardNo) ? bankCardNo : String.format("%s %s %s",
        bankCardNo.substring(0, beginIndex),
        "******",
        bankCardNo.substring(bankCardNo.length() - endIndex)
    );
  }

  public static String maskMobile(String mobile) {
    if (!isEmpty(mobile)) {
      if (mobile.length() < 11) {
        return String.format("%s****%s", mobile.substring(0, 2), mobile.substring(mobile.length() - 3));
      }
      return String.format("%s****%s", mobile.substring(0, 3), mobile.substring(mobile.length() - 4));
    }
    return mobile;
  }

  public static String maskName(String name) {
    if (name == null) {
      return null;
    } else if (name.length() <= 1) {
      return name;
    } else if (name.length() == 2) {
      return "*" + name.substring(1, 2);
    } else {
      return name.length() == 3 ? "**" + name.substring(2, 3) : "**" + name.substring(name.length() - 2, name.length());
    }
  }

  public static String maskCertificateNo(String certNo, String certType) {
    if (isEmpty(certNo)) {
      return certNo;
    } else {
      return CertTypeEnum.TYPE_00.getCode().equals(certType)
          ? maskIdentity(certNo) : maskIdentity(certNo);
    }
  }

  private static String maskIdentity(String certNo) {
    if (!isEmpty(certNo)) {
      StringBuilder builder = new StringBuilder();

      for (int i = 0; i < certNo.length() - 7; ++i) {
        builder.append("*");
      }

      return String.format("%s%s%s", certNo.substring(0, 3), builder.toString(), certNo.substring(certNo.length() - 4));
    }
    return certNo;
  }

  public static enum CertTypeEnum {
    TYPE_00("00", "身份证"),
    TYPE_01("01", "户口本"),
    TYPE_02("02", "军人身份证"),
    TYPE_03("03", "警察证"),
    TYPE_04("04", "港、澳居民往来内地通行证"),
    TYPE_05("05", "台湾居民来往大陆通行证"),
    TYPE_06("06", "护照"),
    TYPE_07("07", "工商营业执照"),
    TYPE_08("08", "法人证书"),
    TYPE_09("09", "组织机构代码证"),
    TYPE_10("10", "其他");

    private String code;
    private String value;

    CertTypeEnum(String code, String value) {
      this.code = code;
      this.value = value;
    }

    public String getCode() {
      return this.code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getValue() {
      return this.value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
