package com.example.wechat.filter;

import com.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 用户登录处理：master
 *
 * @author ONEC
 */
@Slf4j
@Configuration
public class CustomerHandlerConfiguration {


    @Autowired
    private CustomerUserService authDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 处理访问认证
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * spring security 全局异常处理
     * 用来解决匿名用户访问无权限资源时的异常
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        log.info("访问接入点处理：{}", DateUtils.getBusinsessDay());
        return (request, response, e) -> {
            request.getRequestDispatcher("/common/center/login").forward(request, response);
        };
    }

    /**
     * 接入过后问题处理
     * 权限不足问题处理
     * 用来解决认证过的用户访问无权限资源时的异常
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        log.info("接入过后问题处理：{}", DateUtils.getBusinsessDay());
        return (request, response, e) -> {
            request.setAttribute("respMsg", "权限不足");
            request.getRequestDispatcher("/common/failed/failed").forward(request, response);
        };
    }

    /**
     * 登录成功后的处理
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("登录成功后的处理：{}", authentication.getDetails());
            request.setAttribute("phone", authentication.getName());
            request.getRequestDispatcher("/common/center/index").forward(request, response);
        };
    }

    /**
     * 登录失败后的处理:
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, e) -> {

            String message = "登录验证失败";
            log.info("登录失败后的处理：{}", e.getMessage());
            if (e instanceof BadCredentialsException) {
                message = "请输入正确的密码";
            }
            if (e instanceof LockedException) {
                message = "该用户已冻结";
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher("/common/center/login").forward(request, response);
        };
    }

    /**
     * 登出成功后的处理
     *
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        log.info("登出成功后的处理：{}", DateUtils.getBusinsessDay());
        return (request, response, authentication) -> {
            request.setAttribute("message", "已退出登录");
            request.getRequestDispatcher("/common/center/login").forward(request, response);
        };
    }
}
