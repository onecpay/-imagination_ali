package com.example.index.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.content.PatternUtil;
import com.common.dto.request.RequestData;
import com.example.index.dao.UserRepository;
import com.example.index.dto.UserInfoDto;
import com.example.index.entity.UserInfo;
import com.example.index.service.user.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
