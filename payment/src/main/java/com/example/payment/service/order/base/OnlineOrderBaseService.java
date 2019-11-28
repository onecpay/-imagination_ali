package com.example.payment.service.order.base;

import com.example.payment.dto.request.OnlineOrderParam;
import com.example.payment.dto.response.ResponseOrder;

/**
 * 交易订单：数据处理service
 *
 * @author ONEC
 */
public interface OnlineOrderBaseService {

    /**
     * 订单数据公共请求方法
     * @param  onlineOrderParam 订单参数
     * @return ResponseOrder
     */
    ResponseOrder execute(OnlineOrderParam  onlineOrderParam);
}
