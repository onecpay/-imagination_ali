package com.example.payment.service.channel.base;

import com.example.payment.dto.response.ResponseMerchant;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 通道方法设计
 *
 * @author ONEC
 */
public interface BaseChannelMerchantService extends InitializingBean {

    /*************************基础信息部分***************************/

    /**
     * 通道小数保留进位方式
     *
     * @return
     */
    public RoundingMode getRoundingMode();


    /*************************商户部分******************************/
    /**
     * 交易商户是否需要向上游报单
     *
     * @return false
     */
    public boolean needRegister();
    /**
     * 通道商户注册通用
     *
     * @param
     * @return
     */
    public ResponseMerchant register();

    /**
     * 通道商户产品开通通用
     */
    public ResponseMerchant productOpen();

    /**
     * 通道商户费率修改/添加通用
     */
    public ResponseMerchant updateMerchantFee();

    /**
     * 通道商户结算修改/添加通用
     */
    public ResponseMerchant updateMerchantSettle();


    /**
     * 商户注册信息查询
     */
    public ResponseMerchant queryMerchantApi();

    /**
     * 商户账户信息查询
     */
    public ResponseMerchant queryMerchantAmountApi();

    /**
     * 结算信息查询
     */
    public ResponseMerchant querySettleApi();

    /**
     * 商户注册异步通知
     */
    public Map<String, String> notifyMerchantApi(HttpServletRequest request);
    /**
     * 商户结算异步通知
     */
    public ResponseMerchant notifySettleApi(HttpServletRequest request);


}
