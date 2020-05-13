package com.example.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * payment 服务状态设计
 *
 * @author ONEC
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusEnum {

    /**
     * 申请状态未处理
     */
    INIT(1, ""),
    /**
     * 申请状态处理中
     */
    DOING,
    /**
     * 申请状态失败
     */
    FAILED,
    /**
     * 申请状态成功
     */
    SUCCESS,
    /**
     * 产品状态冻结
     */
    FREEZE,
    /**
     * 产品状态正常
     */
    AVAILABLE;

    private int index;

    private String msg;


}
