package com.example.payment.service.online.base;

import com.example.payment.dto.request.OnlineParam;
import com.example.payment.dto.response.ResponseOnline;

/**
 * 交易类型：数据处理service
 *
 * @author ONEC
 */
public interface OnlineBaseService {

    /**
     * 对外请求方法
     *
     * @param onlineParam 订单参数
     * @return ResponseOrder
     */
    ResponseOnline execute(OnlineParam onlineParam);
}
