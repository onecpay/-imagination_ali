//package com.example.wechat.filter;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Iterator;
//
///**
// * 自定义决策器：用户权限验证：
// *
// * @author ONEC
// */
//@Component
//public class PermissionAccessDeniedHandler implements AccessDecisionManager {
//
//    /**
//     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
//     *
//     * @param authentication 包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
//     * @param o              就是FilterInvocation对象，可以得到request等web资源
//     * @param collection     configAttributes是本次访问需要的权限
//     */
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//        if (null == collection || 0 >= collection.size()) {
//            return;
//        } else {
//            String needRole;
//            for (Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
//                needRole = iter.next().getAttribute();
//
//                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
//                    if (needRole.trim().equals(grantedAuthority.getAuthority().trim())) {
//                        return;
//                    }
//                }
//            }
//            throw new AccessDeniedException("当前访问没有权限");
//        }
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
