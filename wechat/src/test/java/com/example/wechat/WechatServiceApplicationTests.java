package com.example.wechat;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.wechat.client.product.ProductClient;
import com.example.wechat.controller.center.CustomerController;
import com.example.wechat.controller.center.LoginController;
import com.example.wechat.dao.master.CustomerMapper;
import com.example.wechat.dao.master.CustomerPermissionMapper;
import com.example.wechat.dto.request.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatServiceApplicationTests {

    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CustomerPermissionMapper permissionMapper;

    @Resource
    LoginController productClient;


    @Test
    public void contextLoads() {
        //customerMapper.findCustomerPhone("15611321516");
        //permissionMapper.selectPermissionCustomer(12L);
        //permissionMapper.select(null);
        CustomerDto re = new CustomerDto();
        re.setPhone("15611321516");
        re.setType("templateId_reg");

//        ResponseData responseData = productClient.requestCode(re);
//        System.out.println("返回数据：" + responseData);
    }

}
