package com.example.index.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限信息.
 *
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_manage_permission_info")
public class PermissionInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Version
    @Column(name = "version", length = 10)
    private Integer version;

    @Column(name = "permission", length = 56)
    private String permission;

    @Column(name = "permission_code", length = 24)
    private String permissionCode;

    @Column(name = "permission_url", length = 56)
    private String permissionUrl;

    @Column(name = "rootFlag")
    private Boolean rootFlag;

    @Column(name = "status", length = 56)
    private String status;

    @Column(name = "remark", length = 56)
    private String remark;

}