package com.colak.springcloudgatewaytutorial.apigateway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("apiRoutes")
@Getter
@Setter
@AllArgsConstructor
public class ApiRoute {
    @Id
    private String id;
    private String routeIdentifier;
    private String uri;
    private String method;
    private String path;
}
