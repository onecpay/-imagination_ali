package com.example.gateway.filter;


import com.example.gateway.service.AgentAuthorizeService;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤器：只有设置确定的方位程序的时候才会执行
 * 收款服务配置过滤器
 *
 * @author ONEC
 */
@Component
public class PaymentGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private static final String AUTHORIZE_TOKEN = "token";
    private static final String AUTHORIZE_UID = "uid";

    @Autowired
    private AgentAuthorizeService agentAuthorizeService;

    /**
     * 验证机构参数：rsa：
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            String uid = headers.getFirst(AUTHORIZE_UID);
            if (token == null) {
                token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            }
            if (uid == null) {
                uid = request.getQueryParams().getFirst(AUTHORIZE_UID);
            }

            ServerHttpResponse response = exchange.getResponse();
            if (agentAuthorizeService.authorizeAgent()) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
    }
}
