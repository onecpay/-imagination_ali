package com.example.manage.controller.manage;

import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.manage.controller.BaseController;
import com.example.manage.dto.ResponseLayui;
import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.UserInfo;
import com.example.manage.exception.ExceptionHelper;
import com.example.manage.service.user.UserManageService;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

/**
 * 用户权限管理。
 *
 * @author ONEC
 */
@Slf4j
@RequestMapping(value = "user")
@RestController
@ApiModel(value = "用户权限管理")
public class UserInfoController extends BaseController {

    @Resource
    private
    UserManageService userManageService;

    /**
     * 查询当前所有用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "userList")
    public ResponseLayui userList( Integer page, Integer limit) {
        List<UserInfo> userInfoList = userManageService.userList();
        return ResponseLayui.ok(userInfoList, userInfoList.size());
    }

    /**
     * 用户新增
     *
     * @param
     * @return
     */
    @RestfulControllerLog(name = "添加用户信息")
    @RequestMapping(value = "addUser")
    public ResponseLayui addUser(@RequestBody UserInfoDto userInfoDto) {
        try {
            userManageService.addUser(userInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionHelper.handlerException(log, e);
        }
        return ResponseLayui.ok(null, 1);
    }

    /**
     * 用户新增
     *
     * @param
     * @return
     */
    @RestfulControllerLog(name = "修改用户信息")
    @RequestMapping(value = "updateUser")
    public ResponseLayui updateUser(@RequestBody UserInfoDto userInfoDto) {
        try {
            userManageService.updateUserId(userInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionHelper.handlerException(log, e);
        }
        return ResponseLayui.ok(null, 1);
    }
}

