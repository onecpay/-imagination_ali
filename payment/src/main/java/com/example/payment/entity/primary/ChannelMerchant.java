package com.example.payment.entity.primary;

import com.example.payment.enums.ProductEnum;
import com.example.payment.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 在线订单实体
 * 通道商户信息。
 *
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_channel_merchant")
public class ChannelMerchant implements Serializable {

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
     * 平台商户
     */
    @Column(name = "merchant_id", length = 25)
    private Integer merchantId;

    /**
     * 平台商户
     */
    @Column(name = "channel_merchant_no", length = 25)
    private String channelMerchantNo;
    /**
     * 验签key
     */
    @Column(name = "sign_key")
    private String signKey;
    /**
     * 加密key
     */
    @Column(name = "encrypt_key")
    private String encryptKey;

    /**
     * 代理扩展key
     */
    @Column(name = "ext_key")
    private String extKey;
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
    @Column(name = "creator", length = 64)
    private String creator;
    /**
     * 产品备注
     */
    @Column(name = "remark", length = 78)
    private String remark;
}
