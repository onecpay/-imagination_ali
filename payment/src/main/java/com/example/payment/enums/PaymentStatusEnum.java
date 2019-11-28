package com.example.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * payment 服务状态设计
 *
 * @author ONEC
 */

@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {

    /**
     * 申请状态未处理
     */
    INIT,
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


}
