package com.example.payment.service.order;

import com.example.payment.dto.request.OnlineOrderParam;
import com.example.payment.dto.response.ResponseOrder;
import com.example.payment.service.order.base.BaseOrderDefaultService;
import org.springframework.stereotype.Service;

/**
 * 微信业务订单逻辑
 * @author ONEC
 */
@Service
public class OnlineOrderWeixinpayService extends BaseOrderDefaultService {

    @Override
    public ResponseOrder execute(OnlineOrderParam onlineOrderParam) {

        channels.get("").requestAlipay();

        return null;
    }
}
