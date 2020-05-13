package com.example.payment.entity.primary;

import com.example.payment.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 在线订单实体
 * 渠道信息。
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_channel_info")
public class ChannelInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "version")
    @Version
    private Integer version;

    /**
     * 通道编号
     */
    @Column(name = "channel_no", length = 25)
    private String channelNo;
    /**
     * 通道名称
     */
    @Column(name = "channel_name", length = 25)
    private String channelName;

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
