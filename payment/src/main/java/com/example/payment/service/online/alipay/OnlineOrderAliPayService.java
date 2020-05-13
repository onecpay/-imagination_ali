package com.example.payment.service.online.alipay;

import com.example.payment.dto.request.OnlineParam;
import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.service.online.base.BaseOnlineDefaultService;
import org.springframework.stereotype.Service;

/**
 * 处理阿里云订单业务逻辑
 *
 * @author ONEC
 */
@Service
public class OnlineOrderAliPayService extends BaseOnlineDefaultService {

    @Override
    public ResponseOnline execute(OnlineParam onlineParam) {


        String channelNo = "";
        String channelMerchant = getChannelMerchant(channelNo);
        // 分配刀订单路由：
        channelOrder.get(channelMerchant).requestAlipay();
        return null;
    }
}
