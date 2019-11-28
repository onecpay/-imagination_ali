package com.example.payment.service.online;

import com.example.payment.dto.response.ResponseMerchant;
import com.example.payment.dto.response.ResponseOrder;
import com.example.payment.service.online.base.BaseChannelDefaultService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 先锋支付上游通道对接
 * @author ONEC
 */
@Service("channel_xf")
public class OnlineChannelXfServiceImpl extends BaseChannelDefaultService {


    @Override
    public ResponseMerchant register() {
        return null;
    }

    @Override
    public ResponseMerchant productOpen() {
        return null;
    }

    @Override
    public ResponseMerchant updateMerchantFee() {
        return null;
    }

    @Override
    public ResponseMerchant updateMerchantSettle() {
        return null;
    }

    @Override
    public ResponseOrder requestWeiXin() {
        return null;
    }

    @Override
    public ResponseOrder requestAlipay() {
        return null;
    }

    @Override
    public ResponseOrder requestUnionPay() {
        return null;
    }

    @Override
    public ResponseOrder requestOpen() {
        return null;
    }

    @Override
    public ResponseOrder requestSms() {
        return null;
    }

    @Override
    public ResponseOrder requestQuickPay() {
        return null;
    }

    @Override
    public Map<String, String> notifyMerchantApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOrder notifyOpenCardApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOrder notifyOnlineOrderApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseOrder notifySettleApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseMerchant queryMerchantApi() {
        return null;
    }

    @Override
    public ResponseMerchant queryMerchantAmountApi() {
        return null;
    }

    @Override
    public ResponseMerchant querySettleApi() {
        return null;
    }

    @Override
    public ResponseOrder queryOpenCardApi() {
        return null;
    }

    @Override
    public ResponseOrder queryOnlineOrderApi() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
