package com.example.manage.dto;

import com.example.manage.enums.ManageEnum;
import lombok.Data;

/**
 * 用户数据信息dto
 */
@Data
public class UserInfoDto {

    private Long id;

    private Integer version;


    private String userName;


    private String phone;


    private String email;


    private String salt;


    private String password;


    private String userNo;


    private ManageEnum status;

    private String createTime;

    private String updateTime;

}
