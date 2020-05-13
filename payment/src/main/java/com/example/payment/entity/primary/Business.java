package com.example.payment.entity.primary;


import com.example.payment.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 业务范围：
 *
 * @author ONEC
 */
@Entity
@Table(name = "t_business_info")
@Data
public class Business implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "version")
    @Version
    private Integer version;

    /**
     * 业务名称
     */
    @Column(name = "business_name")
    private String businessName;

    /**
     * 业务编号
     */
    @Column(name = "business_code", length = 4)
    private Integer businessCode;
    /**
     * 业务编号
     */
    @Column(name = "creator", length = 32)
    private String creator;
    /**
     * 备注
     */
    @Column(name = "remark", length = 32)
    private String remark;
    /**
     * 创建日期
     */
    @Column(name = "create_date", length = 32)
    private Date crateDates;


    @Enumerated(EnumType.STRING)
    @Column(name = "business_status", length = 32)
    private StatusEnum businessStatus;


}
