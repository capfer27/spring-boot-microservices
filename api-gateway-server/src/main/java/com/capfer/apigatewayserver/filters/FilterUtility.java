package com.capfer.apigatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

    // TODO: this should be static instead. BAD CODE!!!!!
    public final String CORRELATION_ID = "capferbank-correlation-id";

    public String getCorrelationId(HttpHeaders httpHeaders) {
        var correlationId = httpHeaders.get(CORRELATION_ID);
        if (correlationId != null && !correlationId.isEmpty()) {
            return correlationId.stream()
                   .findFirst()
                   .get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String headerName, String headerValue) {
        return exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header(headerName, headerValue)
                        .build()
                )
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
