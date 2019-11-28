package com.example.manage.entity;

import com.example.manage.enums.ManageEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ONEC
 */
@Data
@Entity
@Table(name = "t_manage_user_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password")
    private String password;

    @Column(name = "userNo")
    private String userNo;

    @Column(name = "status")
    private ManageEnum status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}