package com.example.wechat.entity.security;

import com.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author ONEC
 */
@Table(name = "t_customer_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerPermission extends BaseEntity implements Serializable {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 客户id 主键
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 权限主键id
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;


}