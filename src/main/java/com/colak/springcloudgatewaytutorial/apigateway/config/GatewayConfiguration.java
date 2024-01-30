package com.colak.springcloudgatewaytutorial.apigateway.config;

import com.colak.springcloudgatewaytutorial.apigateway.service.RouteService;
import com.colak.springcloudgatewaytutorial.apigateway.service.impl.ApiRouteLocatorImpl;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    /*
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route("order-service1",
                        route -> route.path("/order1s/**")
                                .filters(filter -> {
                                    filter.addResponseHeader("res-header", "res-header-value");
                                    return filter;
                                })
                                .uri("http://localhost:8081"))
                .build();
    }
    */

    /**
     * RouteService is my code to access mongo db
     * <p>
     * RouteLocatorBuilder is a builder class provided by Spring Cloud Gateway to facilitate the construction of routes
     * fluently and programmatically.
     */
    @Bean
    public RouteLocator routeLocator(RouteService routeService, RouteLocatorBuilder routeLocationBuilder) {
        return new ApiRouteLocatorImpl(routeLocationBuilder, routeService);
    }
}
