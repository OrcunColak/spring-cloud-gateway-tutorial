package com.colak.springcloudgatewaytutorial.apigateway.service.impl;

import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import com.colak.springcloudgatewaytutorial.apigateway.repository.RouteRepository;
import com.colak.springcloudgatewaytutorial.apigateway.service.RouteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Flux<ApiRoute> getAll() {
        return this.routeRepository.findAll();
    }

    @Override
    public Mono<ApiRoute> create(ApiRoute apiRoute) {
        return this.routeRepository.save(apiRoute);
    }

    @Override
    public Mono<ApiRoute> getById(String id) {
        return this.routeRepository.findById(id);
    }
}
