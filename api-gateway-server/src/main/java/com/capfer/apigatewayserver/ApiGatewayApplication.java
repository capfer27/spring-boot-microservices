package com.capfer.apigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

    /**
     * Конфигурация маршрутов для API Gateway, которая перенаправляет запросы на соответствующие микросервисы
     * в зависимости от пути запроса. Например, запросы, начинающиеся с /capferbank/accounts/, будут перенаправляться
     * на микросервис Accounts, а запросы, начинающиеся с /capferbank/cards/, будут перенаправляться на микросервис Cards.
     * Фильтры используются для переписывания пути запроса, чтобы удалить префикс /capferbank/ перед отправкой запроса
     * на соответствующий микросервис.
     * Каждый маршрут использует балансировщик нагрузки (lb://) для распределения запросов
     * между экземплярами микросервиса, зарегистрированными в Eureka Server.
     *
     *  English:
     * Route configuration for API Gateway that redirects requests to the corresponding microservices
     * based on the request path. For example, requests starting with /capferbank/accounts/ will be redirected
     * to the Accounts microservice, and requests starting with /capferbank/cards/ will be redirected to the Cards microservice.
     * Filters are used to rewrite the request path to remove the /capferbank/ prefix before sending the request to the corresponding microservice.
     * Each route uses a load balancer (lb://) to distribute requests among instances of the microservice registered in the Eureka Server.
     */
    @Bean
    public RouteLocator capferBankRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( p -> p
                .path("/capferbank/accounts/**")
                .filters(filter ->
                        filter.rewritePath("/capferbank/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                )
                .uri("lb://ACCOUNTS"))
                .route( p -> p
                        .path("/capferbank/cards/**")
                        .filters(filter ->
                                filter.rewritePath("/capferbank/cards/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))
                .route(p -> p
                        .path("/capferbank/loans/**")
                        .filters(filter ->
                                filter.rewritePath("/capferbank/loans/(?<segment>.*)", "/${segment}")
                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))
                .build();
    }
}
