package com.example.manage.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据包装
 *
 * @author ONEC
 */
@Data
public class ResponseLayui<T> implements Serializable {

    private String msg;

    private int code;

    private double count;

    private T data;

    public ResponseLayui() {
    }

    private ResponseLayui(String msg, int code, double count, T data) {
        this.msg = msg;
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public static ResponseLayui<Object> ok(Object o, double size) {
        return new ResponseLayui<>("SUCCESS", 0, size, o);
    }

}
