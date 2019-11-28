package com.common.utils;
/** @Auther: hebing @Date: 2018/11/29 18:36 @Description: */
import com.alibaba.fastjson.JSONObject;

/** @author: jiabing @Date: 2018/11/29 18:36 @Description: */
public class ExceptionResponseUtil {

  public static String FAIL_CODE = "20000";
  public static JSONObject innerException = new JSONObject();

  static {
    innerException.put("sub_code", FAIL_CODE);
    innerException.put("sub_msg", "INNER_EXCEPTION");
  }
}
