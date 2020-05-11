package com.example.payment.service.channel.base;

import com.example.payment.dto.response.ResponseOnline;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;

/**
 * 订单通道方法设计
 *
 * @author ONEC
 */
public interface BaseChannelOrderService extends InitializingBean {

    /**
     * 通道小数保留进位方式
     *
     * @return
     */
    public RoundingMode getRoundingMode();

    /**
     * 微信交易通用类
     */
    public ResponseOnline requestWeiXin();

    /**
     * 支付宝交易通用类
     */
    public ResponseOnline requestAlipay();

    /**
     * 银联二维码交易通用类
     */
    public ResponseOnline requestUnionPay();

    /**
     * 快捷支付开通银行卡
     */
    public ResponseOnline requestOpen();

    /**
     * 快捷支付短信验证码通用类
     */
    public ResponseOnline requestSms();

    /**
     * 快捷支付交易通用类
     *
     * @param
     * @return
     */
    public ResponseOnline requestQuickPay();

    /**
     * 绑卡异步通知
     */
    public ResponseOnline notifyOpenCardApi(HttpServletRequest request);

    /**
     * 交易异步通知
     */
    public ResponseOnline notifyOnlineOrderApi(HttpServletRequest request);

    /**
     * 商户结算异步通知
     */
    public ResponseOnline notifySettleApi(HttpServletRequest request);

    /**
     * 商户绑卡信息查询
     */
    public ResponseOnline queryOpenCardApi();

    /**
     * 订单信息查询
     */
    public ResponseOnline queryOnlineOrderApi();


}
