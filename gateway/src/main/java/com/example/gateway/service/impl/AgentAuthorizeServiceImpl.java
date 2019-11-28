package com.example.gateway.service.impl;

import com.example.gateway.service.AgentAuthorizeService;
import org.springframework.stereotype.Service;

/**
 * @author ONEC
 */
@Service
public class AgentAuthorizeServiceImpl implements AgentAuthorizeService {


    @Override
    public boolean authorizeAgent() {
        return false;
    }
}
