package com.example.payment.entity.primary;


import com.example.payment.enums.PaymentStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 行业类别码路由：
 *
 * @author ONEC
 */
@Entity
@Table(name = "t_payment_business")
@Data
public class BusinessRoute implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id" ,unique = true)
    private Long id;

    @Column(name = "version")
    @Version
    private Integer version;

    /**
     * 行业名称
     */
    @Column(name = "business_name", nullable = true)
    private String businessName;

    /**
     * 行业编号
     */
    @Column(name = "business_code", length = 32)
    private String businessCode;

    /**
     * 通道编号
     */
    @Column(name = "channel_code", length = 32)
    private String channelCode;

    /**
     * 通道行业编号
     */
    @Column(name = "channel_business", length = 32)
    private String channelBusiness;

    /**
     * 通道行业优先级
     */
    @Column(name = "priority", length = 32)
    private String priority;

    /**
     * 创建日期
     */
    @Column(name = "create_date", length = 32)
    private Date crateDates;

    /**
     * 支持银行
     */
    @Column(name = "support_bank", length = 32)
    private String supportBank;

    /**
     * 支持银行编号
     */
    @Column(name = "support_code", length = 32)
    private String supportCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_status", length = 32)
    private PaymentStatusEnum businessStatus;


}
