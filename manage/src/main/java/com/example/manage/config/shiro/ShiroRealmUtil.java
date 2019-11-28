package com.example.manage.config.shiro;

import com.example.manage.entity.*;
import com.example.manage.enums.ManageEnum;
import com.example.manage.service.user.UserLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 设计角色以及权限接口
 *
 * @author 26500
 */
public class ShiroRealmUtil extends AuthorizingRealm {

    /**
     * 用于用户查询
     */
    @Autowired
    private UserLoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        //String name =
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 查询用户角色
        List<UserRole> userInfos = loginService.findUserRoleByUserId(userInfo.getId());
        if (null == userInfos || 0 == userInfos.size()) {
            return simpleAuthorizationInfo;
        }
        for (UserRole userRole : userInfos) {
            // 查询角色
            RoleInfo roleInfo = loginService.findRoleByRoleId(userRole.getRoleId());
            simpleAuthorizationInfo.addRole(roleInfo.getRoleCode());

            for (RolePermission rolePermission : loginService.findRolePermissionByRoleId(userRole.getRoleId())) {
                PermissionInfo permissionInfo = loginService.findPermissionById(rolePermission.getPermissionId());
                simpleAuthorizationInfo.addStringPermission(permissionInfo.getPermissionCode());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登陆认证
     * <p>
     * subject.user(usernamePasswordToken);(验证该方法)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 获取用户信息
        String name = token.getPrincipal().toString();
        UserInfo userInfo = loginService.findUserName(name);
        if (null == userInfo) {
            throw new UnknownAccountException();
        }
        if (ManageEnum.FREEZE.equals(userInfo.getStatus())) {
            throw new LockedAccountException();
        }
        // 获取用户的盐值
        ByteSource salt = ByteSource.Util.bytes(userInfo.getSalt());
        // 这里验证authenticationToken和simpleAuthenticationInfo的信息（此处设置的为：user：subject.getPrincipal()
        // 获取到的是user对象 ）
        return new SimpleAuthenticationInfo(
                userInfo, userInfo.getPassword(), salt, getName());

    }
}

