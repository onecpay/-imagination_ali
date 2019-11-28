package com.example.index.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_manage_user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

}