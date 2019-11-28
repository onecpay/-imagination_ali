package com.common.dto.response;

import com.common.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据返回：
 *
 * @author ONEC
 * @since 2019-07-16
 */
@Data
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

    private final static Long serialVersionUID = 1111111111111111L;

    private Integer subCode;

    private String subMessage;

    private T data;

    public static ResponseData ok() {
        return new ResponseData(ResultCodeEnum.SUCCESS);
    }

    public static ResponseData<String> ok(String url) {
        return new ResponseData<>(ResultCodeEnum.SUCCESS, url);
    }

    public static ResponseData error() {
        return new ResponseData(ResultCodeEnum.FAILED);
    }

    public static ResponseData<String> error(String message) {
        ResultCodeEnum code = ResultCodeEnum.FAILED;
        return new ResponseData<>(code, message);
    }

    public ResponseData(ResultCodeEnum resultCode) {
        setResultCode(resultCode);
    }

    public ResponseData(ResultCodeEnum resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCodeEnum resultCodeEnum) {
        this.subCode = resultCodeEnum.getCode();
        this.subMessage = resultCodeEnum.getMessage();
    }
}
