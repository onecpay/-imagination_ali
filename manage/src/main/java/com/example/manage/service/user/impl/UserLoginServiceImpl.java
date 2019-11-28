package com.example.manage.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.content.PatternUtil;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.common.utils.DateUtils;
import com.common.utils.UUIDGenerator;
import com.example.manage.client.product.ProductClient;
import com.example.manage.dao.UserRepository;
import com.example.manage.dao.UserRoleRepository;
import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.*;
import com.example.manage.enums.ManageEnum;
import com.example.manage.service.user.UserLoginService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运营登录sevice
 *
 * @author ONEC
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private ProductClient productClient;

    /**
     * 登录验证获取用户信息
     *
     * @param username
     * @return saleInfo
     */
    @Override
    public UserInfo findUserName(String username) {
        // 邮箱验证
        LOGGER.info("管理系统登录用户参数：{}", username);
        UserInfo userInfo;
        if (username.matches(PatternUtil.email)) {
            userInfo = userRepository.findByEmail(username);
        } else if (username.matches(PatternUtil.phone)) {
            userInfo = userRepository.findByPhone(username);
        } else {
            userInfo = userRepository.findByUserName(username);
        }
        return userInfo;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return SaleInfo
     */
    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {

        String salt = UUIDGenerator.generate();
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        String password = new SimpleHash("MD5", userInfo.getPassword(), byteSource, 1024).toHex();
        userInfo.setCreateTime(DateUtils.getBusinsessDate());
        userInfo.setUpdateTime(DateUtils.getBusinsessDate());
        userInfo.setVersion(1);
        userInfo.setUserNo(userInfo.getPhone());
        userInfo.setSalt(salt);
        userInfo.setPassword(password);
        userInfo.setStatus(ManageEnum.AVAILABLE);
        userRepository.save(userInfo);
        LOGGER.info("用户{}:设置成功：", userInfo.getUserName());

        // 设置默认角色
//        UserRole userRole = new UserRole();
//        userRole.setUserId(userInfo.getId());
//        userRole.setRoleId(1L);
//        userRoleRepository.save(userRole);
//        LOGGER.info("用户：{}:默认角色设置成功", userInfo.getUserName());
        return userInfo;
    }

    @Override
    public List<UserRole> findUserRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public RoleInfo findRoleByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<RolePermission> findRolePermissionByRoleId(Long roleId) {
        return null;
    }

    @Override
    public PermissionInfo findPermissionById(Long id) {
        return null;
    }

    /**
     * 发送验证码。
     * @param userInfoDto
     * @return
     */
    @Override
    public boolean requestCode(UserInfoDto userInfoDto) {
        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(userInfoDto));
        ResponseData responseData = productClient.requestCode(requestData);
        return false;
    }

    /**
     * 检验验证码.
     * @param userInfoDto   1
     * @return
     */
    @Override
    public boolean checkCode(UserInfoDto userInfoDto) {
        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(userInfoDto));
        return false;
    }



}
