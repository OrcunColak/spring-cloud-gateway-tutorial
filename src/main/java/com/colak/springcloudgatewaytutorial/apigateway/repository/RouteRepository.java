package com.colak.springcloudgatewaytutorial.apigateway.repository;

import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RouteRepository extends ReactiveMongoRepository<ApiRoute, String> {
}
