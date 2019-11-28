package com.example.manage.config.shiro;

import com.example.manage.entity.UserInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * shiro 登陆成功重写方法
 *
 * @author 26500
 */
public class ShiroSuccessUtil extends FormAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSuccessUtil.class);

    /**
     * 重写登陆成功方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpSession httpSession = httpRequest.getSession();

        UserInfo userInfo = (UserInfo) subject.getPrincipal();

        LOGGER.info("当前管理者：{} 登录成功", userInfo.getUserName());
        httpSession.setAttribute("name", userInfo.getUserName());
        WebUtils.issueRedirect(httpRequest, response, "/index/success");
        return true;
    }
}
