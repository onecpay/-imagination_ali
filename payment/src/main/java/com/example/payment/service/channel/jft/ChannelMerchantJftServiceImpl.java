package com.example.payment.service.channel.jft;

import com.example.payment.dto.response.ResponseMerchant;
import com.example.payment.service.channel.base.BaseChannelMerchantDefaultService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.util.Map;


/**
 * 佳付通通道对接
 *
 * @author ONEC
 */
@Service("MERCHANT_JFT")
public class ChannelMerchantJftServiceImpl extends BaseChannelMerchantDefaultService {


    @Override
    public RoundingMode getRoundingMode() {
        return null;
    }

    @Override
    public boolean needRegister() {
        return true;
    }

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
    public Map<String, String> notifyMerchantApi(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseMerchant notifySettleApi(HttpServletRequest request) {
        return null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
