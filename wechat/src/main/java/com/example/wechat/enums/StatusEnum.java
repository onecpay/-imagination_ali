package com.example.wechat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 状态控制枚举
 *
 * @author ONEC
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {


    /**
     * 状态可用
     */
    AVAILABLE(10000, "可用"),
    /**
     * 状态冻结
     */
    FREEZE(10001, "冻结");

    private Integer code;

    private String message;

}
