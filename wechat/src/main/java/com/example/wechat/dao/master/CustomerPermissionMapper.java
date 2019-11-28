package com.example.wechat.dao.master;

import com.example.wechat.entity.security.CustomerPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ONEC
 */
@Repository
public interface CustomerPermissionMapper extends Mapper<CustomerPermission> {

    /**
     *权限查询
     * @param customerId 用户id
     * @return CustomerPermission
     */
    List<CustomerPermission> selectPermissionCustomer(@Param("customerId") Long customerId);

    /**
     * 权限查询
     * @param permissionId 权限id
     * @return CustomerPermission
     */
    List<CustomerPermission> selectCustomerPermission(@Param("permissionId") Long permissionId);
}