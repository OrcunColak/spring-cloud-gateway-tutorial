package com.colak.springcloudgatewaytutorial.apigateway.service.impl;

import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import com.colak.springcloudgatewaytutorial.apigateway.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * RouteLocator is an interface provided by Spring Cloud Gateway that defines a contract for classes responsible for
 * locating and defining routes.
 * <p>
 * RouteLocatorBuilder is a builder class provided by Spring Cloud Gateway to facilitate the construction of routes
 * fluently and programmatically.
 * <p>
 * It is often used in combination with the RouteLocator interface to define the routing configuration for the API gateway.
 */
@RequiredArgsConstructor
@Service
public class ApiRouteLocatorImpl implements RouteLocator {
    private final RouteLocatorBuilder routeLocatorBuilder;
    private final RouteService routeService;

    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        return routeService.getAll()
                .map(
                        apiRoute -> routesBuilder.route(
                                String.valueOf(apiRoute.getRouteIdentifier()),
                                predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)
                        )
                )
                .collectList()
                .flatMapMany(
                        builders -> routesBuilder
                                .build()
                                .getRoutes());
    }

    private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
        if (StringUtils.hasLength(apiRoute.getMethod())) {
            booleanSpec.and()
                    .method(apiRoute.getMethod());
        }
        booleanSpec.filters(f -> f.stripPrefix(1));
        return booleanSpec.uri(apiRoute.getUri());
    }

    @Override
    public Flux<Route> getRoutesByMetadata(Map<String, Object> metadata) {
        return RouteLocator.super.getRoutesByMetadata(metadata);
    }
}