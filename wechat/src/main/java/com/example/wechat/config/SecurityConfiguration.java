package com.example.wechat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * 配置security 安全框架:
 * https://www.cnblogs.com/ll409546297/p/10267926.html
 *
 * @author ONEC
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//, securedEnabled = true
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * 跨域
     */
    @Resource
    private CorsFilter corsFilter;
    /**
     * 认证
     */
    @Resource
    private DaoAuthenticationProvider daoAuthenticationProvider;

    /**
     * 认证
     */
//    @Resource
//    AuthenticationProviderInterceptor authenticationProviderInterceptor;

    /**
     * 认证成功
     */
    @Resource
    private AuthenticationSuccessHandler successHandler;
    /**
     * 认证失败
     */
    @Resource
    private AuthenticationFailureHandler failureHandler;
    /**
     * 登出成功
     */
    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    /**
     * 权限不知：accessDeniedHandler ：处理页面：可以重写：处理ajax：
     */
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 认证EntryPoint
     */
    @Resource
    private AuthenticationEntryPoint entryPoint;

    /**
     * 自定义认证
     *
     * @param builder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/api/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/**")
                .antMatchers("/configuration/ui")
                .antMatchers("/configuration/master")
                .antMatchers("/static/**")
                .antMatchers("/index/**")
                .antMatchers("/manage/doRegister")
                .antMatchers("/common/**")
                .antMatchers("/wx/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(corsFilter, CsrfFilter.class)

                // 处理登录：
                .formLogin().loginPage("/common/center/login")
                .usernameParameter("phone")
                .passwordParameter("password")
                .loginProcessingUrl("/manage/doLogin")
                .successHandler(successHandler)
                .failureHandler(failureHandler)

                // 处理登出
                .and()
                .logout().logoutUrl("/manage/logout")
                .logoutSuccessHandler(logoutSuccessHandler)

                // 处理权限: 未前后端分离
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                  // jwt 适合前后端分离
//                .addFilter(new JwtLoginFilter(authenticationManager()))
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // 其他所有的都的要过滤
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .headers()
                .frameOptions()
                .disable()

                // 启用或者关闭session: 启用session： 此项目非前后分离
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }
}
