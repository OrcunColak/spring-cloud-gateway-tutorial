package com.colak.springcloudgatewaytutorial.apigateway.handler;

import com.colak.springcloudgatewaytutorial.apigateway.config.GatewayRoutesRefresher;
import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import com.colak.springcloudgatewaytutorial.apigateway.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiRouteHandler {
    private final RouteService routeService;

    private final GatewayRoutesRefresher gatewayRoutesRefresher;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<ApiRoute> apiRoute = serverRequest.bodyToMono(ApiRoute.class);
        return apiRoute.flatMap(route ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(routeService.create(route), ApiRoute.class));
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        log.info("getById is called");
        final String apiId = serverRequest.pathVariable("routeId");
        Mono<ApiRoute> apiRoute = routeService.getById(apiId);
        return apiRoute.flatMap(route -> ServerResponse.ok()
                        .body(fromValue(route)))
                .switchIfEmpty(ServerResponse.notFound()
                        .build());
    }

    public Mono<ServerResponse> refreshRoutes(ServerRequest serverRequest) {
        log.info("refreshRoutes is called");
        gatewayRoutesRefresher.refreshRoutes();
        return ServerResponse.ok().body(BodyInserters.fromObject("Routes reloaded successfully"));
    }
}
