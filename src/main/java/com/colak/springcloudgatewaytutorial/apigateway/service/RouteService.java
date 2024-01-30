package com.colak.springcloudgatewaytutorial.apigateway.service;

import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteService {
    Flux<ApiRoute> getAll();

    Mono<ApiRoute> create(ApiRoute apiRoute);

    Mono<ApiRoute> getById(String id);
}
