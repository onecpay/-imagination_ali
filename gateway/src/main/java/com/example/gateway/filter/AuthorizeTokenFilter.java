package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 自定义全局过滤器
 *
 * @author ONEC
 * @Component: 注解生效
 */
@Component
public class AuthorizeTokenFilter implements GlobalFilter, Ordered {


    /**
     * 配置主要过滤
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
