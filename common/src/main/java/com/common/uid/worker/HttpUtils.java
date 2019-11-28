package com.common.uid.worker;


import com.common.utils.ConstUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * http原生工具类
 *
 * @author leifu @Date 2015年1月15日 @Time 上午9:42:47
 */
public final class HttpUtils {

  public static String doPost(String reqUrl, Map<String, String> parameters) {
    return doPost(
        reqUrl, parameters, "UTF-8", ConstUtils.HTTP_CONN_TIMEOUT, ConstUtils.HTTP_SOCKET_TIMEOUT);
  }

  public static String doPost(String reqUrl, Map<String, String> parameters, String encoding) {
    return doPost(
        reqUrl, parameters, encoding, ConstUtils.HTTP_CONN_TIMEOUT, ConstUtils.HTTP_SOCKET_TIMEOUT);
  }

  public static String doPost(
      String reqUrl,
      Map<String, String> parameters,
      String encoding,
      int connectTimeout,
      int readTimeout) {
    HttpURLConnection urlConn = null;
    try {
      urlConn = sendPost(reqUrl, parameters, encoding, connectTimeout, readTimeout);
      String responseContent = getContent(urlConn, encoding);
      return responseContent.trim();
    } finally {
      if (urlConn != null) {
        urlConn.disconnect();
        urlConn = null;
      }
    }
  }

  private static HttpURLConnection sendPost(
      String reqUrl,
      Map<String, String> parameters,
      String encoding,
      int connectTimeout,
      int readTimeout) {
    HttpURLConnection urlConn = null;
    try {
      String params = generatorParamString(parameters, encoding);
      URL url = new URL(reqUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("POST");
      urlConn.setConnectTimeout(connectTimeout);
      urlConn.setReadTimeout(readTimeout);
      urlConn.setDoOutput(true);
      // String按照字节处理是一个好方法
      byte[] b = params.getBytes(encoding);
      urlConn.getOutputStream().write(b, 0, b.length);
      urlConn.getOutputStream().flush();
      urlConn.getOutputStream().close();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
    return urlConn;
  }

  private static String getContent(HttpURLConnection urlConn, String encoding) {
    try {
      String responseContent = null;
      InputStream in = urlConn.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(in, encoding));
      String tempLine = rd.readLine();
      StringBuffer tempStr = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        tempStr.append(tempLine);
        tempStr.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = tempStr.toString();
      rd.close();
      in.close();
      return responseContent;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * @param link
   * @param encoding
   * @return
   */
  public static String doGet(String link, String encoding, int connectTimeout, int readTimeout) {
    HttpURLConnection conn = null;
    try {
      URL url = new URL(link);
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(connectTimeout);
      conn.setReadTimeout(readTimeout);
      BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      for (int i = 0; (i = in.read(buf)) > 0; ) {
        out.write(buf, 0, i);
      }
      out.flush();
      String s = new String(out.toByteArray(), encoding);
      return s;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    } finally {
      if (conn != null) {
        conn.disconnect();
        conn = null;
      }
    }
  }

  /**
   * UTF-8编码
   *
   * @param link
   * @return
   */
  public static String doGet(String link) {
    return doGet(link, "UTF-8", ConstUtils.HTTP_CONN_TIMEOUT, ConstUtils.HTTP_SOCKET_TIMEOUT);
  }

  /**
   * 将parameters中数据转换成用"&"链接的http请求参数形式
   *
   * @param parameters
   * @return
   */
  private static String generatorParamString(Map<String, String> parameters, String encoding) {
    StringBuffer params = new StringBuffer();
    if (parameters != null) {
      for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
        String name = iter.next();
        String value = parameters.get(name);
        params.append(name + "=");
        try {
          params.append(URLEncoder.encode(value, encoding));
        } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
          String message = String.format("'%s'='%s'", name, value);
          throw new RuntimeException(message, e);
        }
        if (iter.hasNext()) {
          params.append("&");
        }
      }
    }
    return params.toString();
  }
}
