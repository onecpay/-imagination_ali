package com.example.wechat.entity;

import com.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author ONEC
 */
@Data
@Table(name = "t_wechat_log")
public class WechatLog extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private Integer version;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 服务地址
     */
    @Column(name = "request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Column(name = "request_param")
    private String requestParam;

    /**
     * 返回参数
     */
    @Column(name = "response_param")
    private String responseParam;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    private static final long serialVersionUID = 1L;


    public WechatLog() {
    }

    public WechatLog(Integer version, String method, String ip, String requestUrl, String requestParam, String responseParam, Date createTime, Date updateTime, String remark) {
        this.version = version;
        this.method = method;
        this.ip = ip;
        this.requestUrl = requestUrl;
        this.requestParam = requestParam;
        this.responseParam = responseParam;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }
}