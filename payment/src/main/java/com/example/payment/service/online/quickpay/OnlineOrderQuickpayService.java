package com.example.payment.service.online.quickpay;

import com.example.payment.dto.request.OnlineParam;
import com.example.payment.dto.request.QuickPayParam;
import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.service.online.base.BaseOnlineDefaultService;
import org.springframework.stereotype.Service;

/**
 * 快捷业务订单逻辑
 *
 * @author ONEC
 */
@Service
public class OnlineOrderQuickpayService extends BaseOnlineDefaultService {


    @Override
    public ResponseOnline execute(OnlineParam onlineParam) {

        // 快捷路由：
        if (onlineParam instanceof QuickPayParam) {

        }
        return null;
    }
}
