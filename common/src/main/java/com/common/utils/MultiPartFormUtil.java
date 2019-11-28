package com.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/** @Description: 解决下载文件中文名称乱码 @Author: 吴世攀 @CreateDate: 2018/11/20 16:50 */
public class MultiPartFormUtil {

  /**
   * 解决各种浏览器下载文件，中文文件名乱码
   *
   * @param request
   * @param response
   * @param fileName 文件名
   * @throws UnsupportedEncodingException
   */
  public static void setCharacter(
      HttpServletRequest request, HttpServletResponse response, String fileName)
      throws UnsupportedEncodingException {
    if (request == null || response == null || fileName == null) {
      return;
    }

    response.setContentType("binary/octet-stream;charset=UTF-8");
    // 判断浏览器版本，设置相应编码格式
    String userAgent = request.getHeader("User-Agent");

    if (null != userAgent) {
      if(-1 != userAgent.indexOf("Firefox")) {
        fileName =
            "=?UTF-8?B?"
                + (new String(
                    org.apache.commons.codec.binary.Base64.encodeBase64(
                        fileName.getBytes("UTF-8"))))
                + "?=";
      } else if(-1 != userAgent.indexOf("Chrome")) {
        fileName = new String(fileName.getBytes(), "ISO8859-1");
      } else { // IE7+
        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        fileName = StringUtils.replace(fileName, "+", "%20");
      }
    }
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
  }
}
