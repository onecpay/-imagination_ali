package com.example.manage.client.wechat;

import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.manage.exception.ManageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author ONEC
 */
@Slf4j
@Component
@ComponentScan(basePackages = "com.example.manage.client")
public class WechatClientFallback implements WechatClient {

    @Override
    public ResponseData requestCode(RequestData requestData) {
        log.info("产品服务请求降级：");
        throw new ManageException(20001, "服务请求异常");
    }
}
