package com.example.payment.service.route;

import com.example.payment.dao.primary.BusinessRouteRepository;
import com.example.payment.entity.primary.BusinessRoute;
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
