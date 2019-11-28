package com.example.payment.service.order.base;

import com.example.payment.service.online.base.BaseChannelDefaultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author ONEC
 */
public abstract class BaseOrderDefaultService implements OnlineOrderBaseService{

    /**
     *  自动注入bean
     */
    @Autowired
    protected Map<String , BaseChannelDefaultService> channels;
}
