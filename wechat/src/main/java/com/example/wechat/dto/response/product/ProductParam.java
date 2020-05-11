package com.example.wechat.dto.response.product;

import com.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ONEC
 */
@Data
public class ProductParam {


    /**
     * 产品编码
     */
    private String code;

    /**
     * 是否星选
     */
    private Integer stars;

    /**
     * 备注信息
     */
    private String remark;


    private String serialUrl;


    private String needId;

    /**
     * 结算金额的类型P/I
     */
    private String amountType;

    /**
     * 对账文件姓名脱敏类型
     */
    private String nameType;

    /**
     * 对账文件手机号类型
     */
    private String phoneType;

    /**
     * 对账文件身份证号类型
     */
    private String idType;

    /**
     * 通道类型
     */
    private String channelType;

    /**
     * 分享背景图链接地址
     */
    private String shareImage;


}