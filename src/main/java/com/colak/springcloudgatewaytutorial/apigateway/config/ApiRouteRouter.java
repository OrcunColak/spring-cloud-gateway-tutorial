package com.colak.springcloudgatewaytutorial.apigateway.config;

import com.colak.springcloudgatewaytutorial.apigateway.handler.ApiRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ApiRouteRouter {
    // http://localhost:8080/routes/refresh-routes
    // http://localhost:8080/routes/1
    @Bean
    public RouterFunction<ServerResponse> route(ApiRouteHandler apiRouteHandler) {
        return RouterFunctions.route(POST("/routes")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::create)
                .andRoute(GET("/routes/{routeId}")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::getById)
                .andRoute(GET("/routes/refresh-routes")
                        .and(accept(MediaType.APPLICATION_JSON)), apiRouteHandler::refreshRoutes);
    }
}
