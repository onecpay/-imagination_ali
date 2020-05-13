package com.example.payment.entity.primary;

import com.example.payment.enums.ProductEnum;
import com.example.payment.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 在线订单实体
 * 通道费率信息。
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_channel_info_fee")
public class ChannelInfoFee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "version")
    @Version
    private Integer version;

    /**
     * 通道编号id
     */
    @Column(name = "channel_id", length = 25)
    private String channelId;

    /**
     * 产品编号
     */
    @Column(name = "product", length = 25)
    private ProductEnum product;

    /**
     * 交易费率
     */
    @Column(name = "rate")
    private BigDecimal rate;

    /**
     * 提现费率
     */
    @Column(name = "fix_fee")
    private BigDecimal fixFee;

    /**
     * 交易费率
     */
    @Column(name = "rate_t1")
    private BigDecimal rateT1;

    /**
     * 提现费率
     */
    @Column(name = "rate_t0")
    private BigDecimal rateT0;
    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;
    /**
     * 创建时间
     */
    @Column(name = "update_date")
    private Date updateDate;
    /**
     * 通道状态
     */
    @Column(name = "status")
    private StatusEnum status;
    /**
     * 银行名称
     */
    @Column(name = "creator",length = 64)
    private String creator;
    /**
     * 产品备注
     */
    @Column(name = "remark",length = 78)
    private String remark;
}
