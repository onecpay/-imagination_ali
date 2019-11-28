package com.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 自定义跟踪日志生成request
 *
 * @author yangzhongying
 * @version 1.0
 * @date 2018-07-04 0:10
 */
public class LogsUtils {

  public static final String REQUEST_ID_KEY = "requestId";
  public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static String getRequestId(HttpServletRequest request) {
    String requestId = null;
    String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
    String headerRequestId = request.getHeader(REQUEST_ID_KEY);
    if (parameterRequestId == null && headerRequestId == null) {
      requestId = UUID.randomUUID().toString().replaceAll("\\-", "");
    } else {
      requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
    }
    threadLocal.set(requestId);
    return requestId;
  }

  public static void destroy() {
    threadLocal.remove();
  }
}
