package com.example.payment.service.online.base;

import com.example.payment.dto.response.ResponseMerchant;
import com.example.payment.dto.response.ResponseOrder;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 上游通道通用类默认实现方法
 *
 * @author ONEC
 */
public abstract class BaseChannelDefaultService implements OnlineChannelBaseService {
    @Override
    public RoundingMode getRoundingMode() {
        return RoundingMode.HALF_UP;
    }

    @Override
    public boolean needRegister() {
        return false;
    }

}
