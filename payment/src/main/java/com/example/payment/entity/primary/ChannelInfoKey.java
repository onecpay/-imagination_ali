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
 *
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_channel_info_Key")
public class ChannelInfoKey implements Serializable {

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
     * 代理扩展编号
     */
    @Column(name = "ext_no", length = 25)
    private String extNo;
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
