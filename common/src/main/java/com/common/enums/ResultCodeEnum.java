package com.common.enums;

import lombok.*;

/**
 * payment 服务状态设计
 *
 * @author ONEC
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(10000, "成功"),
    /**
     * 失败
     */
    FAILED(20000, "失败"),
    /**
     * 处理中
     */
    DOING(30000, "处理中"),
    /**
     * 拒绝访问
     */
    UNAUTHORIZED(403, "拒绝访问"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "路径不存在"),
    /**
     * 缺少token
     */
    NO_TOKEN(401, "认证失败，缺少头部token值"),
    /**
     * 服务器内部错误
     */
    SERVER_ERROR(500, "系统错误");

    private Integer code;

    private String message;


}
