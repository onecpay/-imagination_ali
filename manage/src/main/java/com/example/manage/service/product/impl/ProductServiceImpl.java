package com.example.manage.service.product.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.request.product.ProductDetailReq;
import com.common.dto.response.ResponseData;
import com.example.manage.client.product.ProductClient;
import com.example.manage.dto.ProductInfoDto;
import com.example.manage.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ONEC
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductClient productClient;


    @Override
    public List<ProductInfoDto> productList(Integer pageNo, Integer pageSize) {
        RequestData requestData = new RequestData();
        ProductDetailReq productDetailReq = ProductDetailReq
                .builder()
                .type("P000A")
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();
        requestData.setBizContent(JSONObject.toJSONString(productDetailReq));
        log.info("管理平台获取产品：参数：{}", requestData);
        ResponseData productInfoDtos = productClient.productList(requestData);
        List<ProductInfoDto> dtosData = (List<ProductInfoDto>) productInfoDtos.getData();
        return dtosData;
    }
}
