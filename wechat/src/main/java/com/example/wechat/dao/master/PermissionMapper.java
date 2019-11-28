package com.example.wechat.dao.master;

import com.example.wechat.entity.security.Permission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ONEC
 */
@Repository
public interface PermissionMapper extends Mapper<Permission> {
}