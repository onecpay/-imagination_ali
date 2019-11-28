package com.example.wechat.interceptor;

import com.example.wechat.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author tangj
 * @date 2018/1/21 22:27
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String ip = IpUtil.getIpAddrByRequest(request);
        logger.info("用户访问地址: {}, 来路地址: {}", uri, ip);
        // 处理404 等：
        if (errorCodeList.contains(response.getStatus())) {
            request.setAttribute("respMsg", response.getStatus());
            response.sendRedirect("/wechat/common/failed/failed");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String ip = IpUtil.getIpAddrByRequest(request);
        Integer statusCode = response.getStatus();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
