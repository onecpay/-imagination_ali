package com.example.wechat.dao.master;

import com.example.wechat.entity.security.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ONEC
 */
@Repository
public interface CustomerMapper extends Mapper<Customer> {
    /**
     * 手机号查询用户信息：
     * @param phone 手机号
     * @return Customer
     */
    Customer findCustomerPhone(@Param("phone") String phone);

    /**
     * 邮箱查询用户信息：
     * @param email 邮箱
     * @return Customer
     */
    Customer findCustomerEmail(@Param("email") String email);
}