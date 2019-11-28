package com.example.manage.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_manage_role_permission")
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

}