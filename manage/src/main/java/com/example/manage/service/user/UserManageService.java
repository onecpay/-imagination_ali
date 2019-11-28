package com.example.manage.service.user;


import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.*;

import java.util.List;

/**
 * @author ONEC
 */
public interface UserManageService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserInfo> userList();

    /**
     * id 查用户
     *
     * @param userId
     * @return
     */
    UserInfo selectById(Long userId);

    /**
     * id 修改用户
     *
     * @param userInfo
     * @return
     */
    void updateUserId(UserInfo userInfo);


    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    void addUser(UserInfoDto userInfo);
}
