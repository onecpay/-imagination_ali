package com.example.wechat.entity.customer;

import com.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "t_wechat_customer_extra")
public class CustomerExtra extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * verson
     */
    private Integer version;

    /**
     * 会员编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 身份id
     */
    @Column(name = "person_id")
    private String personId;

    /**
     * 卡号
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 银行名称
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 联行号
     */
    @Column(name = "bank_code")
    private String bankCode;

    /**
     * 银行简称
     */
    @Column(name = "bank_no")
    private String bankNo;

    /**
     * 用户身份图片url
     */
    @Column(name = "customer_url")
    private String customerUrl;

    /**
     * 卡片url
     */
    @Column(name = "card_url")
    private String cardUrl;

    /**
     * 修改日趋
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建日
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 身份姓名
     */
    @Column(name = "person_name")
    private String personName;

    private static final long serialVersionUID = 1L;


    /**
     * 身份姓名
     */
    @Transient
    private List imageIds;

}