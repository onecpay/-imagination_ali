package com.example.wechat.controller.banner;

import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.wechat.controller.BaseController;
import com.example.wechat.entity.customer.BannerInfo;
import com.example.wechat.entity.customer.CustomerExtra;
import com.example.wechat.service.BannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录注册管理：
 *
 * @author ONEC
 */
@Api(value = "user manage：", tags = "用户登录管理菜单")
@Controller
@RequestMapping(value = "banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    /**
     * 补充信息.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "extra")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @RestfulControllerLog(name = "extraInfo")
    public ResponseData extraInfo(@RequestBody CustomerExtra customerExtra, HttpServletRequest request) {

        return ResponseData.ok("信息补充完成");
    }

    /**
     * 查看我的信息.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "bannerInfo")
    @RestfulControllerLog(name = "bannerInfo")
    public ModelAndView bannerInfo(HttpServletRequest request) {
        List<BannerInfo> bannerInfoList = bannerService.bannerInfo();
//        request.setAttribute("images", customerUrl);
//        request.setAttribute("bannerList", customerExtra);
        return respModelView("permission/personal/customerInfo", "查询成功");
    }
}
