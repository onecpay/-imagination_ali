package com.example.payment.service.impl;

import com.example.payment.dao.primary.BusinessRouteRepository;
import com.example.payment.entity.primary.BusinessRoute;
import com.example.payment.service.BusinessRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ONEC
 */
@Service
public class BusinessRouteServiceImpl implements BusinessRouteService {

    @Autowired
    BusinessRouteRepository businessRouteRepository;

    @Override
    public List<BusinessRoute> businessList() {
        return businessRouteRepository.findAll();
    }
}
