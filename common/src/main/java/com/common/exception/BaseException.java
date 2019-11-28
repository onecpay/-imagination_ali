package com.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常公共处理类
 *
 * @author ONEC
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    public Integer code;

    public String msg;

}
