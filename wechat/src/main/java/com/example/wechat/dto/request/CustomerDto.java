package com.example.wechat.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户与页面交互dto
 *
 * @author ONEC
 */
@Data
@ApiModel(value = "customer", description = "用户交互实体")
public class CustomerDto {

    /**
     * 用户手机：
     */
    @NotNull
    @ApiModelProperty(name = "phone", value = "15611321516")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "2650020302@qq.com")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty(name = "password")
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     * 获取短信的类型
     */
    private String type;
}
