package com.example.manage.entity;

import com.example.manage.enums.ManageEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_manage_role_info")
public class RoleInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "version")
    @org.springframework.data.annotation.Version
    private Integer version;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "status")
    private ManageEnum status;

}