package com.colak.springcloudgatewaytutorial.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * intercepts the requests and responses and logs them into the console.
 */
@Component
@Slf4j
public class RequestAndResponseLogGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Pre Filter Logic: Request path: {}", exchange.getRequest().getPath());
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
                log.info("Post Filter Logic: HTTP Status Code: {}",
                        Objects.requireNonNull(exchange.getResponse().getStatusCode()))));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
