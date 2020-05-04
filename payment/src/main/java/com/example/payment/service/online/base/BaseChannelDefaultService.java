package com.example.payment.service.online.base;

import java.math.RoundingMode;

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
