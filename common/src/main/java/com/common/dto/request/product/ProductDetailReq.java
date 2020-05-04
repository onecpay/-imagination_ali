package com.common.dto.request.product;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductDetailReq {
    /**
     * 产品详情主键
     */
    private Long id;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 产品类型
     */
    private String type;

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
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 通道产品编号
     */
    private String channelCode;

    /**
     * 通道类型
     */
    private String channelType;

    private static final long serialVersionUID = 1L;

    /**
     * 当前第几页：
     */
    private Integer pageNo;
    /**
     * 每页数据大小：
     */
    private Integer pageSize;
}
