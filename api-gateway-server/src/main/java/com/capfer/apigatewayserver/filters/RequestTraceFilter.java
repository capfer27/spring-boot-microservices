package com.capfer.apigatewayserver.filters;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(headers)) {
            logger.debug("capferbank-correlation-id found in the headers (RequestTraceFilter): {}. ", filterUtility.getCorrelationId(headers));
        } else {
            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            logger.debug("capferbank-correlation-id generated in the headers (RequestTraceFilter): {}. ", correlationId);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders httpHeaders) {
        return filterUtility.getCorrelationId(httpHeaders) != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
