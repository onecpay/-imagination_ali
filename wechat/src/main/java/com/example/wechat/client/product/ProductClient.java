package com.example.wechat.client.product;


import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ONEC
 */
@FeignClient(name = "product", path = "product", fallback = ProductClientFallback.class)
public interface ProductClient {

    /**
     * 发送短信验证码。
     *
     * @param requestData a
     * @return respdate
     */
    @ResponseBody
    @RequestMapping(value = "/code/requestCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String requestCode(@RequestBody RequestData requestData);

    /**
     * 获取短信验证码。
     *
     * @param requestData a
     * @return respdate
     */
    @ResponseBody
    @RequestMapping(value = "/code/checkSmsCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String checkSmsCode(@RequestBody RequestData requestData);


    /**
     * 获取产品明细。
     *
     * @param requestData a
     * @return respdate
     */
    @ResponseBody
    @RequestMapping(value = "/detail/productList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONObject productList(@RequestBody RequestData requestData);

    /**
     * 获取产品明细。
     *
     * @param requestData a
     * @return respdate
     */
    @ResponseBody
    @RequestMapping(value = "/detail/productId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONObject productDetail(@RequestBody RequestData requestData);
}
