package com.example.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ONEC
 */

@Getter
@AllArgsConstructor
@ToString
public enum ProductEnum {

  /**
   * 申请状态未处理
   */
  WEIXIN("微信支付", 1),
  /**
   * 申请状态处理中
   */
  ALIPAY("阿里支付", 2),
  /**
   * 申请状态失败
   */
  QUICK("快捷支付", 3),
  /**
   * 申请状态成功
   */
  UNIONPAY("云闪付", 4);

  private String mesg;

  private Integer index;

}
