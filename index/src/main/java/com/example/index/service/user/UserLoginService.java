package com.example.index.service.user;


import com.example.index.dto.UserInfoDto;
import com.example.index.entity.UserInfo;

/**
 * @author ONEC
 */
public interface UserLoginService {

    /**
     * 获取用户名
     *
     * @param username
     * @return
     */
    UserInfo findUserName(String username);

    /**
     * 验证短信验证码.
     *
     * @param userInfoDto   1
     * @return
     */
    boolean checkCode(UserInfoDto userInfoDto);

}
