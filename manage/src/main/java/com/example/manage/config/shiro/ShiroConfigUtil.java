package com.example.manage.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 26500 @ shiro 登陆管理权限配置
 * @since 2018-12-03
 */
@Configuration
public class ShiroConfigUtil {

    @Bean
    public ShiroFilterFactoryBean shiroFactory(org.apache.shiro.mgt.SecurityManager securityManager) {
        // 配置拦截器 string
        Map<String, String> filterString = new HashMap<String, String>();
        // 配置不会被拦截的连接顺序判断
        filterString.put("/static/**", "anon");
        // 配置的退出过滤 拦截器 其中的实现shiro 已经处理
        filterString.put("/logout", "logout");
        // 添加成功跳转的过滤器
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterString.put("/**", "authc");
        // 配置拦截器 string
        Map<String, Filter> filter = new HashMap<String, Filter>();
        filter.put("authc", new ShiroSuccessUtil());

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 如果不设置默认会自动寻找web 工程下的login
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 登陆成功跳转地址
        shiroFilterFactoryBean.setSuccessUrl("/manage/manageMenu");
        // 认证失败页面配置
        shiroFilterFactoryBean.setUnauthorizedUrl("/toFailed");
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setFilters(filter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterString);

        return shiroFilterFactoryBean;
    }

    /**
     * 将自己的验证方式加入容器
     */
    @Bean
    public ShiroRealmUtil shiroRealmUtil() {
        ShiroRealmUtil shiroRealmUtil = new ShiroRealmUtil();
        shiroRealmUtil.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealmUtil;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     *
     * @return
     */
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealmUtil());
        return securityManager;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效 如：@RequiresRoles
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 哈希密码比较器。在myShiroRealm中作用参数使用 登陆时会比较用户输入的密码，跟数据库密码配合盐值salt解密后是否一致。
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用md5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5( md5(""));
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动创建代理
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 注入bean，页面标签权限验证逻辑.
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
