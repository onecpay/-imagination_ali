package com.example.gateway.service.impl;

import com.example.gateway.service.AgentAuthorizeService;
import org.springframework.stereotype.Service;

/**
 * @author ONEC
 * dobbo 的使用：@service 使用dobbo ：
 * 使用@referce 自动注入
 */
@Service
public class AgentAuthorizeServiceImpl implements AgentAuthorizeService {


    @Override
    public boolean authorizeAgent() {
        return false;
    }
}
