package com.example.product.entity;

import com.common.entity.BaseEntity;
import com.example.product.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author ONEC
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_product_detail")
public class ProductDetail extends BaseEntity implements Serializable {
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
     * 产品类型
     */
    private TypeEnum type;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品编码
     */
    private String code;

    /**
     * 通道名称
     */
    private String channel;

    /**
     * 产品地址（上游）
     */
    private String url;

    /**
     * 产品标签
     */
    private String logo;

    /**
     * 产品状态
     */
    private String status;

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
     * 通道产品编号
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 通道类型
     */
    @Column(name = "channel_type")
    private String channelType;

    private static final long serialVersionUID = 1L;


}