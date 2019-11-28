package com.example.manage.service.user;


import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.*;

import java.util.List;

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
     * 获取用户名
     *
     * @param UserInfo
     * @return
     */
    UserInfo saveUserInfo(UserInfo UserInfo);

    /**
     * 获取用户名
     *
     * @param userId
     * @return
     */
    List<UserRole> findUserRoleByUserId(Long userId);

    /**
     * 获取用户名
     *
     * @param roleId
     * @return
     */
    RoleInfo findRoleByRoleId(Long roleId);

    /**
     * 获取用户名
     *
     * @param roleId
     * @return
     */
    List<RolePermission> findRolePermissionByRoleId(Long roleId);

    /**
     * 获取用户名
     *
     * @param id
     * @return
     */
    PermissionInfo findPermissionById(Long id);


    /**
     * 获取短信验证码.
     *
     * @param
     * @param userInfoDto
     * @return
     */
    boolean requestCode(UserInfoDto userInfoDto);

    /**
     * 验证短信验证码.
     *
     * @param userInfoDto   1
     * @return
     */
    boolean checkCode(UserInfoDto userInfoDto);

}
