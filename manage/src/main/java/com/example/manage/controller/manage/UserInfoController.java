package com.example.manage.controller.manage;

import com.example.manage.controller.BaseController;
import com.example.manage.dto.ResponseLayui;
import com.example.manage.dto.UserInfoDto;
import com.example.manage.entity.UserInfo;
import com.example.manage.service.user.UserManageService;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户权限管理。
 *
 * @author ONEC
 */
@RequestMapping(value = "user")
@RestController
@ApiModel(value = "用户权限管理")
public class UserInfoController extends BaseController {

    @Resource
    UserManageService userManageService;

    /**
     * 查询当前所有用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "userList")
    public ResponseLayui userList() {
        List<UserInfo> userInfoList = userManageService.userList();
        return ResponseLayui.ok(userInfoList, userInfoList.size());
    }

    /**
     * 用户新增
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addUser")
    public ResponseLayui addUser(@RequestBody UserInfoDto userInfoDto) {
        userManageService.addUser(userInfoDto);
        return null;
    }


}

