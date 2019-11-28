package com.example.product.controller.code;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.product.controller.BaseController;
import com.example.product.dto.SmsCodeDto;
import com.example.product.service.code.SmsCodeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ONEC
 * @since 20190913
 */
@ApiModel(value = "短信服务接口")
@RestController
@RequestMapping(value = "/code")
public class SmsCodeController extends BaseController {

    @Resource
    private SmsCodeService smsCodeService;

    /**
     * 发送短信验证码/
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation(value = "短信统一接口")
    @PostMapping(value = "/requestCode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData requestCode(@RequestBody RequestData requestData) {

        SmsCodeDto smsCodeDto = JSONObject.parseObject(requestData.getBizContent(), SmsCodeDto.class);

        boolean success = smsCodeService.tencentSend(smsCodeDto);
        if (success) {
            return this.success("短息发送成功");
        }
        return this.success("短信测试发送失败");
    }

    /**
     * 发送短信验证码/
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation(value = "短信统一验证接口")
    @PostMapping(value = "/checkSmsCode", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData checkSmsCode(@RequestBody RequestData requestData) {
        SmsCodeDto smsCodeDto = JSONObject.parseObject(requestData.getBizContent(), SmsCodeDto.class);
        boolean success = smsCodeService.checkCode(smsCodeDto.getType(), smsCodeDto.getPhone(), smsCodeDto.getSmsCode());
        if (success) {
            return this.success("短息验证成功");
        }
        return this.success("短信验证失败");
    }
}
