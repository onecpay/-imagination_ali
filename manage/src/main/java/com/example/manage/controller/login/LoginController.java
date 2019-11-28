package com.example.manage.controller.login;

import com.common.dto.response.ResponseData;
import com.example.manage.dto.UserInfoDto;
import com.example.manage.service.user.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ONEC
 * @since 2018-10-22
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserLoginService userLoginService;

    /**
     * 跳转登录页面 get 跳转登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public ModelAndView toLoginManage(HttpServletRequest request, ModelMap model) {
        ModelAndView moView = new ModelAndView();
        moView.setViewName("login/login");
        return moView;
    }

    /**
     * 认证页面 post 验证登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String loginManage(String username, String password, HttpServletRequest request, ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException user) {
            model.addAttribute("message", "用户名输入有误");
        } catch (IncorrectCredentialsException ics) {
            model.addAttribute("message", "用户名或者密码错误");
        } catch (LockedAccountException lock) {
            model.addAttribute("message", "用户状态已锁定");
        } catch (ExcessiveAttemptsException excess) {
            model.addAttribute("message", "登录次数超限");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            model.addAttribute("message", "用户名或密码错误");
        }
        return "login/login";
    }

    /**
     * shiro 认证成功跳转
     *
     * @return
     */
    @RequestMapping(value = "/index/success")
    public String success(HttpServletRequest request) {
        return "index/index";
    }
    // real 方法中已经实现了登陆所以在此处只需要获取登陆异常即可
    // Subject subject = SecurityUtils.getSubject();
    // UsernamePasswordToken usernamePasswordToken =
    // new UsernamePasswordToken(username, password);
    // // 用户验证 捕获异常提示相关信息
    // try {
    // subject.user(usernamePasswordToken);
    // session.setAttribute("user", usernamePasswordToken.getPrincipal());
    // } catch (AuthenticationException e) {
    // e.printStackTrace();
    // }
    // // 根据shiro返回的异常类路径判断，抛出指定异常信息

    /**
     * 跳转登录失败/或者未授权页面
     *
     * @return
     */
    @RequestMapping(value = "/toFailed", method = RequestMethod.GET)
    public ModelAndView toFailed(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        ModelAndView moView = new ModelAndView();
        moView.setViewName("/manage/ManageLogin");// 成功跳转到主页
        return moView;
    }

    /**
     * shiro 登出操作
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/html/manage/ManageLogin";
    }


    /**
     * 添加运营管理人员（当前权限为：admin）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addOperation")
    @RequiresPermissions("PN0001")
    public boolean addOperation(HttpServletRequest request, RequestParam requestParam) {
        HttpSession httpSession = request.getSession();
        LOGGER.info("当前用户：{}:添加用户ip:{}", httpSession.getAttribute("showname"), request.getRemoteAddr());
        LOGGER.info("获取到的参数：{}:", requestParam);
        //userLoginService.addSaleInfo(requestParam);
        return true;
    }

    /**
     * 请求验证码。
     *
     * @param userInfoDto
     * @return
     */
    @RequestMapping(value = "requestCode")
    @RequiresPermissions("PN0001")
    public ResponseData requestCode(@RequestBody UserInfoDto userInfoDto) {
        userLoginService.requestCode(userInfoDto);
        return ResponseData.ok();
    }
}
