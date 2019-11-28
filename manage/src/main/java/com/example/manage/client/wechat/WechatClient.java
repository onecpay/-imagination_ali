package com.example.manage.client.wechat;


import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * wechat 管理
 * @author ONEC
 */
@FeignClient(name = "wechat", path = "wechat", fallback = WechatClientFallback.class)
public interface WechatClient {

    /**
     * 获取短信验证码。
     *
     * @param requestData a
     * @return respdate
     */
    @RequestMapping(value = "/code/requestCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseData requestCode(@RequestBody RequestData requestData);
}
