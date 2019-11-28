package com.example.payment.service;

import com.example.payment.entity.primary.BusinessRoute;

import java.util.List;

/**
 * @author ONEC
 */
public interface BusinessRouteService {

    /**
     * 获取所有的行业类别
     * @return
     */
    List<BusinessRoute> businessList();

}
