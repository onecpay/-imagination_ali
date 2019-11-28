package com.common.utils;

import com.common.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * web 统一API响应结果封装
 *
 * @author yangzhongying
 */
@Data
public class Response<T> implements Serializable {

  private static final long serialVersionUID = 783015033603078674L;
  private int code;
  private String msg;
  private T data;

  public static Response ok() {
    return ok("");
  }

  public static Response ok(Object o) {
    return new Response(ResultCodeEnum.SUCCESS, o);
  }

  public static Response error() {
    return new Response(ResultCodeEnum.FAILED);
  }

  public static Response error(ResultCodeEnum code) {
    return error(code, "");
  }

  public static Response error(String message) {
    return new Response(ResultCodeEnum.FAILED, message);
  }

  public static Response error(ResultCodeEnum code, Object o) {
    return new Response(code, o);
  }

  public Response(ResultCodeEnum resultCode) {
    setResultCode(resultCode);
  }

  public Response(ResultCodeEnum resultCode, T data) {
    setResultCode(resultCode);
    this.data = data;
  }

  public void setResultCode(ResultCodeEnum resultCode) {
    this.code = resultCode.getCode();
    this.msg = resultCode.getMessage();
  }

  @Override
  public String toString() {
    return "{" +
            "\"code\":" + code +
            ", \"msg\":\"" + msg + '\"' +
            ", \"data\":\"" + data + '\"' +
            '}';
  }
}
