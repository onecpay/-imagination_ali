package com.example.payment.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * 订单参数
 *
 * @author ONEC
 */
@Data
@Builder
public class OnlineParam<T> {
    /**
     * 机构号
     */
    public String orgNo;

    /**
     * 商户号
     */
    public String merchantNo;

    /**
     * 机构订单号
     */
    public String outOrderNo;

    /**
     * 通道订单号
     */
    public String OrderNo;

    /**
     * 平台流水号
     */
    public String serialNo;

}
