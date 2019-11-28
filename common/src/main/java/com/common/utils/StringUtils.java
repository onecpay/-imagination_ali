package com.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author seejoke
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

  private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

  private static final char SEPARATOR = '_';
  private static final String CHARSET_NAME = "UTF-8";

  /**
   * 转换为字节数组
   *
   * @param str 字符串
   * @return byte[]
   */
  public static byte[] getBytes(String str) {
    if (str != null) {
      try {
        return str.getBytes(CHARSET_NAME);
      } catch (UnsupportedEncodingException e) {
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * 转换为字节数组
   *
   * @param bytes 字节数组
   * @return String
   */
  public static String toString(byte[] bytes) {
    try {
      return new String(bytes, CHARSET_NAME);
    } catch (UnsupportedEncodingException e) {
      return EMPTY;
    }
  }

  /**
   * 是否包含字符串
   *
   * @param str 验证字符串
   * @param strs 字符串组
   * @return 包含返回true
   */
  public static boolean inString(String str, String... strs) {
    if (str != null) {
      for (String s : strs) {
        if (str.equals(trim(s))) {
          return true;
        }
      }
    }
    return false;
  }

  /** 转换为Double类型 */
  public static Double toDouble(Object val) {
    if (val == null) {
      return 0D;
    }
    try {
      return Double.valueOf(trim(val.toString()));
    } catch (Exception e) {
      return 0D;
    }
  }

  /**
   * 转换为Float类型
   *
   * @param val
   * @return
   */
  public static Float toFloat(Object val) {
    return toDouble(val).floatValue();
  }

  /** 转换为Long类型 */
  public static Long toLong(Object val) {
    return toDouble(val).longValue();
  }

  /** 转换为Integer类型 */
  public static Integer toInteger(Object val) {
    return toLong(val).intValue();
  }

  /** 获得用户远程地址 */
  public static String getRemoteAddr(HttpServletRequest request) {
    String remoteAddr = request.getHeader("X-Real-IP");
    if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("X-Forwarded-For");
    } else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("Proxy-Client-IP");
    } else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("WL-Proxy-Client-IP");
    }
    return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *     "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCamelCase(String s) {
    if (s == null) {
      return null;
    }

    s = s.toLowerCase();

    StringBuilder sb = new StringBuilder(s.length());
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == SEPARATOR) {
        upperCase = true;
      } else if (upperCase) {
        sb.append(Character.toUpperCase(c));
        upperCase = false;
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *     "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCapitalizeCamelCase(String s) {
    if (s == null) {
      return null;
    }
    s = toCamelCase(s);
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  /**
   * 驼峰命名法工具
   *
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *     "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toUnderScoreCase(String s) {
    if (s == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      boolean nextUpperCase = true;

      if (i < (s.length() - 1)) {
        nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
      }

      if ((i > 0) && Character.isUpperCase(c)) {
        if (!upperCase || !nextUpperCase) {
          sb.append(SEPARATOR);
        }
        upperCase = true;
      } else {
        upperCase = false;
      }

      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }

  /**
   * 转换为JS获取对象值，生成三目运算返回结果
   *
   * @param objectString 对象串 例如：row.user.id 返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
   */
  public static String jsGetVal(String objectString) {
    StringBuilder result = new StringBuilder();
    StringBuilder val = new StringBuilder();
    String[] vals = split(objectString, ".");
    for (int i = 0; i < vals.length; i++) {
      val.append("." + vals[i]);
      result.append("!" + (val.substring(1)) + "?'':");
    }
    result.append(val.substring(1));
    return result.toString();
  }

  public static String decodeHttpParam(HttpServletRequest request, String param) {
    if (!isBlank(param)) {
      String value = request.getParameter(param);
      if (!isBlank(value)) {
        try {
          String newString = new String(value.getBytes("iso8859-1"), "utf-8");
          return newString.trim();
        } catch (Exception e) {

        }
      }
    }
    return null;
  }

  public static String subSplitToInStr(String str) {
    StringBuffer conditionStr = new StringBuffer();
    String resStr = "";
    if (str != null && !"".equals(str)) {
      conditionStr.append("('");
      String[] strs = str.split(",");
      for (String strp : strs) {
        conditionStr.append(strp).append("','");
      }
      resStr = conditionStr.substring(0, conditionStr.length() - 2) + ")";
    }
    return resStr;
  }

  /**
   * 获取请求头Origin，是否在跨域配置中
   *
   * @param request
   * @param webUrl
   * @return String
   */
  public static String getOrigin(HttpServletRequest request, String webUrl) {
    Enumeration en = request.getHeaders("Origin");
    String[] urls = webUrl.split(",");
    while (en.hasMoreElements()) {
      String reqOrigin = (String) en.nextElement();
      for (String url : urls) {
        if (reqOrigin.equals(url)) {
          return reqOrigin;
        }
      }
    }
    return null;
  }

  /** 获取请求头Origin，是否在跨域配置中 */
  public static String getOrigin(ServletRequest req) {
    HttpServletRequest request = (HttpServletRequest) req;
    Enumeration en = request.getHeaders("Origin");
    while (en.hasMoreElements()) {
      return (String) en.nextElement();
    }
    return null;
  }

  /**
   * 将字符串转为数组
   *
   * @param data 数据
   * @param encode 字符编码
   * @return 字节数组
   */
  public static byte[] getBytes(String data, String encode) {
    try {
      if (null == data) {
        return null;
      }
      return data.getBytes(encode);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getBodyString(HttpServletRequest request) {
    ServletInputStream ris = null;
    try {
      ris = request.getInputStream();
      StringBuilder content = new StringBuilder();
      byte[] b = new byte[1024];
      int lens = -1;
      while ((lens = ris.read(b)) > 0) {
        content.append(new String(b, 0, lens));
      }
      return content.toString();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 拼接请求参数为字符串
   *
   * @param sortedParams
   * @return String
   */
  public static String generateSignatureParam(JSONObject sortedParams) {
    StringBuilder content = new StringBuilder();
    List<String> keys = new ArrayList<>(sortedParams.keySet());
    Collections.sort(keys);
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = (String) sortedParams.get(key);
      if (value != null && !"".equals(value)) {
        content.append((i == 0 ? "" : "&") + key + "=" + value);
      }
    }
    return content.toString();
  }

  /**
   * 获取request请求的所有参数
   *
   * @param request
   * @return JSONObject
   */
  public static JSONObject getAllRequestParam(final HttpServletRequest request) {
    JSONObject params = new JSONObject();
    Map requestParams = request.getParameterMap();
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
      }
      params.put(name, valueStr);
    }
    return params;
  }

  /**
   * 生成 HMACSHA256
   *
   * @param data 待处理数据
   * @param key 密钥
   * @return 加密结果
   * @throws Exception
   */
  public static String hashHmac(String data, String key) {
    try {
      Mac sha256Hmac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
      sha256Hmac.init(secretKey);
      byte[] array = sha256Hmac.doFinal(data.getBytes("UTF-8"));
      StringBuilder sb = new StringBuilder();
      for (byte item : array) {
        sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString().toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("关注微信配置签名出异常" + e.getMessage());
      return "";
    }
  }

  public static String getPicSuffix(String imgPath) {
    return imgPath.substring(imgPath.lastIndexOf(".")).
            trim().toLowerCase();
  }
}
