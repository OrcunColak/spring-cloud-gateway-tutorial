package com.colak.springcloudgatewaytutorial.apigateway.config;

import com.colak.springcloudgatewaytutorial.apigateway.entity.ApiRoute;
import com.colak.springcloudgatewaytutorial.apigateway.repository.RouteRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import reactor.core.publisher.Flux;

@Configuration
@EnableMongoRepositories
public class MongoReactiveConfiguration extends AbstractReactiveMongoConfiguration {


    @Value("${spring.data.mongodb.uri}")
    private String mongoDatabaseUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoDatabaseUri);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    @Bean
    @ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner loadData(RouteRepository repository) {
        return args -> {
            // save a couple of users
            var users = Flux.just(
                    new ApiRoute("1","order-service1","http://localhost:8080","GET","/ignored/orders1/**")

            );
            repository.saveAll(users).subscribe();
        };
    }
}
