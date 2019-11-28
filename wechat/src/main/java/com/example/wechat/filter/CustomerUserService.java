package com.example.wechat.filter;

import com.example.wechat.dao.master.CustomerMapper;
import com.example.wechat.dao.master.CustomerPermissionMapper;
import com.example.wechat.dao.master.PermissionMapper;
import com.example.wechat.entity.security.Customer;
import com.example.wechat.entity.security.CustomerPermission;
import com.example.wechat.entity.security.Permission;
import com.example.wechat.enums.StatusEnum;
import com.common.content.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ONEC
 * 用户注册登录控制
 */
@Slf4j
@Service
public class CustomerUserService implements UserDetailsService {

    @Autowired
    private CustomerMapper customerMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private CustomerPermissionMapper customerPermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {

        log.info("微信服务：进行访问认证：{}", param);
        Customer customer;
        if (param.matches(PatternUtil.email)) {
            customer = customerMapper.findCustomerPhone(param);
        } else {
            customer = customerMapper.findCustomerPhone(param);
        }
        if (null == customer) {
            log.info("暂未发现用户：跳转到注册页面：{}", param);
            throw new UsernameNotFoundException("该用户尚未注册");
        }
        // 用户名不存在：用户已经锁定
        if (StatusEnum.FREEZE.equals(customer.getStatus())) {
            log.info("该用户已经锁定：跳转页面：{}", param);
            throw new LockedException("用户已锁定");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        List<CustomerPermission> customerPermissions = customerPermissionMapper.selectPermissionCustomer(customer.getId());
        if (!customerPermissions.isEmpty()) {
            for (CustomerPermission customerPermission : customerPermissions) {
                Permission permission = permissionMapper.selectByPrimaryKey(customerPermission.getPermissionId());
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return new User(customer.getPhone(), customer.getPassword(), simpleGrantedAuthorities);
    }
}
