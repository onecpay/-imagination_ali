package com.example;

import com.common.utils.DateUtils;
import com.example.payment.entity.primary.BusinessRoute;
import com.example.payment.service.BusinessRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentApplicationTest {

    @Autowired
    BusinessRouteService businessRouteService;

//    @Test
//    public void contextLoads() {
//
//       System.out.println("测试数据：开始：{}" + DateUtils.getBusinsessDay());
//       List<BusinessRoute> businessRoutes = businessRouteService.businessList();
//       System.out.println("返回的数据是：" + businessRoutes);
//    }

}
