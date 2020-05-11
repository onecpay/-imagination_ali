package com.example.payment.service.online.base;

import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.service.channel.base.BaseChannelMerchatDefaultService;
import com.example.payment.service.channel.base.BaseChannelOrderDefaultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author ONEC
 */
public abstract class BaseOnlineDefaultService implements OnlineBaseService {

    /**
     * 自动注入bean;
     * 商户通道；
     */
    @Autowired
    protected Map<String, BaseChannelMerchatDefaultService> channelMerchant;

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
