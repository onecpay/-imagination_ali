package com.example.payment.entity.primary;

import com.example.payment.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 在线订单实体
 *
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_payment_order")
public class OnlineOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "version")
    @Version
    private Integer version;

    /**
     * 代理商编号
     */
    @Column(name = "agent_no", length = 25)
    private String agentNo;
    /**
     * 商户编号
     */
    @Column(name = "merchant_no", length = 25)
    private String merchantNo;

    /**
     * 通道上游编号
     */
    @Column(name = "channel_merchant_no", length = 32)
    private String channelMerchantNo;

    /**
     * 通道编号
     */
    @Column(name = "channel_no", length = 25)
    private String channelNo;

    /**
     * 类型码编号
     */
    @Column(name = "business_code", length = 25)
    private String businessCode;

    /**
     * 下游订单号
     */
    @Column(name = "external_num", length = 32)
    private String externalNum;

    /**
     * 上游订单号
     */
    @Column(name = "trade_num", length = 32)
    private String tradeNum;

    /**
     * 平台流水号
     */
    @Column(name = "serial_num", length = 35)
    private String serialNum;

    /**
     * 代理商手续费
     */
    @Column(name = "agent_fee", length = 9)
    private BigDecimal agentFee;


    /**
     * 商户手续费（包含固定值）
     */
    @Column(name = "merchant_fee", length = 9)
    private BigDecimal merchantFee;

    /**
     * 通道手续费（包含固定值）
     */
    @Column(name = "channel_fee", length = 9)
    private BigDecimal channelFee;

    /**
     * 商户固定手续费
     */
    @Column(name = "fix_fee", length = 9)
    private BigDecimal fixFee;

    /**
     * 交易金额
     */
    @Column(name = "amount", length = 9)
    private BigDecimal amount;

    /**
     * 优惠金额
     */
    @Column(name = "coupon_amount", length = 9)
    private BigDecimal couponAmount;

    /**
     * 优惠信息：jsonobject
     */
    @Column(name = "coupon_list",length = 120)
    private String couponList;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 完成时间
     */
    @Column(name = "complete_date")
    private Date completeDate;

    /**
     * 通道完成时间
     */
    @Column(name = "channel_complete_date")
    private Date channelCompleteDate;

    /**
     * 订单过期时间
     */
    @Column(name = "time_expire")
    private Date timeExpire;

    /**
     * 交易订单状态
     */
    @Column(name = "order_status")
    private StatusEnum orderStatus;

    /**
     * 订单出款状态
     */
    @Column(name = "withdrew_status")
    private StatusEnum withDrewStatus;

    /**
     * 交易卡号
     */
    @Column(name = "card_no",length = 32)
    private String cardNo;

    /**
     * 银行名称
     */
    @Column(name = "bank_name",length = 64)
    private String bankName;

    /**
     * 交易手机号
     */
    @Column(name = "phone",length = 16)
    private String phone;

    /**
     * 产品备注
     */
    @Column(name = "remark",length = 78)
    private String remark;
}
