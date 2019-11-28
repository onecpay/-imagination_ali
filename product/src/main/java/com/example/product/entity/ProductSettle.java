package com.example.product.entity;

import com.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * @author ONEC
 */
@Data
@Table(name = "t_product_settle")
public class ProductSettle extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 版本
     */
    @Version
    private Integer version;

    /**
     * 产品编码
     */
    private String code;

    /**
     * 通道结算费
     */
    private Long amount;

    /**
     * 会员结算费
     */
    @Column(name = "customer_price")
    private Long customerPrice;

    /**
     * 非会员结算费
     */
    private Long price;

    /**
     * 结算周期
     */
    @Column(name = "settle_period")
    private Integer settlePeriod;

    /**
     * 修改日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 贷款费率
     */
    @Column(name = "loans_fee")
    private BigDecimal loansFee;

    /**
     * 贷款最高金额
     */
    @Column(name = "loans_max_amount")
    private Double loansMaxAmount;

    private static final long serialVersionUID = 1L;

}