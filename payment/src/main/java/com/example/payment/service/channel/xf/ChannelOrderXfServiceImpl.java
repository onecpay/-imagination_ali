package com.example.payment.service.channel.xf;

import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.service.channel.base.BaseChannelOrderDefaultService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;


/**
 * 先锋支付上游通道对接
 * @author ONEC
 */
@Service("ORDER_XF")
public class ChannelOrderXfServiceImpl extends BaseChannelOrderDefaultService {

    @Override
    public RoundingMode getRoundingMode() {
        return null;
    }

    @Override
    public ResponseOnline requestWeiXin() {
        return null;
    }

    @Override
    public ResponseOnline requestAlipay() {
        return null;
    }

    @Override
    public ResponseOnline requestUnionPay() {
        return null;
    }

    @Override
    public ResponseOnline requestOpen() {
        return null;
    }

    @Override
    public ResponseOnline requestSms() {
        return null;
    }

    @Override
    public ResponseOnline requestQuickPay() {
        return null;
    }

    @Override
    public ResponseOnline notifyOpenCardApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOnline notifyOnlineOrderApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOnline notifySettleApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOnline queryOpenCardApi() {
        return null;
    }

    @Override
    public ResponseOnline queryOnlineOrderApi() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
