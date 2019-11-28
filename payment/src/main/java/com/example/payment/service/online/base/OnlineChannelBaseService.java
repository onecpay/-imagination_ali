package com.example.payment.service.online.base;

import com.example.payment.dto.response.ResponseMerchant;
import com.example.payment.dto.response.ResponseOrder;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 通道方法设计
 *
 * @author ONEC
 */
public interface OnlineChannelBaseService extends InitializingBean {

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

    /*************************交易部分******************************/

    /**
     * 微信交易通用类
     */
    public ResponseOrder requestWeiXin();

    /**
     * 支付宝交易通用类
     */
    public ResponseOrder requestAlipay();

    /**
     * 银联二维码交易通用类
     */
    public ResponseOrder requestUnionPay();

    /**
     * 快捷支付开通银行卡
     */
    public ResponseOrder requestOpen();

    /**
     * 快捷支付短信验证码通用类
     */
    public ResponseOrder requestSms();

    /**
     * 快捷支付交易通用类
     *
     * @param
     * @return
     */
    public ResponseOrder requestQuickPay();

    /*************************通知部分******************************/

    /**
     * 商户注册异步通知
     */
    public Map<String, String> notifyMerchantApi(HttpServletRequest request);

    /**
     * 绑卡异步通知
     */
    public ResponseOrder notifyOpenCardApi(HttpServletRequest request);

    /**
     * 交易异步通知
     */
    public ResponseOrder notifyOnlineOrderApi(HttpServletRequest request);

    /**
     * 商户结算异步通知
     */
    public ResponseOrder notifySettleApi(HttpServletRequest request);

    /*************************查询部分******************************/

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
     * 商户绑卡信息查询
     */
    public ResponseOrder queryOpenCardApi();

    /**
     * 订单信息查询
     */
    public ResponseOrder queryOnlineOrderApi();


}
