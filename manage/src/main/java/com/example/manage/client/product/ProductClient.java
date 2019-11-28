package com.example.manage.client.product;


import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ONEC
 */
@FeignClient(name = "product", path = "product", fallback = ProductClientFallback.class)
public interface ProductClient {

    /**
     * 获取短信验证码。
     *
     * @param requestData a
     * @return respdate
     */
    @RequestMapping(value = "/code/requestCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseData requestCode(@RequestBody RequestData requestData);

    /**
     * 获取短信验证码。
     *
     * @param requestData a
     * @return respdate
     */
    @RequestMapping(value = "/detail/productList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseData productList(@RequestBody RequestData requestData);
}
