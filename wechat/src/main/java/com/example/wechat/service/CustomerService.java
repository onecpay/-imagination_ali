package com.example.wechat.service;

import com.common.dto.response.ResponseData;
import com.example.wechat.dto.request.CustomerDto;
import com.example.wechat.entity.customer.CustomerExtra;
import com.example.wechat.entity.security.Customer;

/**
 * @author ONEC
 */
public interface CustomerService {


    /**
     * 用户注册：分配权限
     *
     * @param customerDto
     * @return Customer
     */
    Customer saveCustomer(CustomerDto customerDto);

    /**
     * 用户注册：补充信息
     *
     * @param customerExtra
     * @return Customer
     */
    CustomerExtra saveCustomerExtra(CustomerExtra customerExtra);

    /**
     * 查询我的信息
     *
     * @param customerDto
     * @return
     */
    CustomerExtra customerInfo(CustomerDto customerDto);

    /**
     * 获取短信验证码
     * @param customerDto
     * @return
     */
    ResponseData requestCode(CustomerDto customerDto);
}
