package com.example.wechat.dto.response;

import com.example.wechat.enums.TypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * 产品返回数据
 * @author ONEC
 */
@Data
public class ProductDetail {


    private long id;
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
     * 产品描述
     */
    private String describe;

    /**
     * 产品状态
     */
    private String status;

    /**
     * 产品创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

}
