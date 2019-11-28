package com.example.manage.service.user.impl;

import com.example.manage.dao.UserRepository;
import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.UserInfo;
import com.example.manage.exception.ManageException;
import com.example.manage.service.user.UserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


/**
 * 用户管理实现
 *
 * @author ONEC
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<UserInfo> userList() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo selectById(Long userId) {
        Optional<UserInfo> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new ManageException(20003, "用户信息查询失败");
    }

    /**
     * jpa 修改：通过设置save 主键保存
     *
     * @param userInfo
     */
    @Override
    public void updateUserId(UserInfo userInfo) {
        userRepository.saveAndFlush(userInfo);
    }

    /**
     * 添加运营管理人员,以及相关的角色
     *
     * @param userInfo
     */
    @Override
    public void addUser(UserInfoDto userInfo) {

    }
}
