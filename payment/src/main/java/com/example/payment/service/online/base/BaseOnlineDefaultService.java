package com.example.payment.service.online.base;

import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.service.channel.base.BaseChannelMerchantDefaultService;
import com.example.payment.service.channel.base.BaseChannelOrderDefaultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author ONEC
 */
public abstract class BaseOnlineDefaultService implements OnlineBaseService {

    private static final String MERCHANT_ = "MERCHANT_";
    private static final String ORDER_ = "ORDER_";

    /**
     * 商户业务
     * @param channelCode
     * @return
     */
    protected String getChannelMerchant(String channelCode) {
        return MERCHANT_ + channelCode;
    }
    /**
     * 订单业务
     * @param channelCode
     * @return
     */
    protected String getChannelOrder(String channelCode) {
        return ORDER_ + channelCode;
    }
    /**
     * 自动注入bean;
     * 商户通道；
     */
    @Autowired
    protected Map<String, BaseChannelMerchantDefaultService> channelMerchant;

    /**
     * 自动注入bean;
     * 商户通道；
     */
    @Autowired
    protected Map<String, BaseChannelOrderDefaultService> channelOrder;


    /**
     * 返回成功数据：
     *
     * @return
     */
    protected ResponseOnline responseOnline() {
        return null;
    }

}
