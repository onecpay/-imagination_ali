package com.example.product.entity;

import com.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author ONEC
 */
@Data
@Table(name = "t_product_param")
public class ProductParam extends BaseEntity implements Serializable {
    /**
     * 产品详情主键
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
     * 是否星选
     */
    private Integer stars;

    /**
     * 产品创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 产品地址（平台）
     */
    @Column(name = "serial_url")
    private String serialUrl;

    /**
     * 是否需要id/Y/N
     */
    @Column(name = "need_id")
    private String needId;

    /**
     * 结算金额的类型P/I
     */
    @Column(name = "amount_type")
    private String amountType;

    /**
     * 对账文件姓名脱敏类型
     */
    @Column(name = "name_type")
    private String nameType;

    /**
     * 对账文件手机号类型
     */
    @Column(name = "phone_type")
    private String phoneType;

    /**
     * 对账文件身份证号类型

     */
    @Column(name = "id_type")
    private String idType;

    /**
     * 通道类型
     */
    @Column(name = "channel_type")
    private String channelType;

    /**
     * 分享背景图链接地址
     */
    @Column(name = "share_image")
    private String shareImage;

    private static final long serialVersionUID = 1L;


}