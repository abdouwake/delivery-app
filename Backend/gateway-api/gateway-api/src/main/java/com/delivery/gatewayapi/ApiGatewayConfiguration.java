package com.delivery.gatewayapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Autowired
    AuthenticationFilter filter;



    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        return builder.routes()
                .route(route -> route.path("/get")
                        .filters(f->f.addRequestHeader("MyHead","MyURI"))
                        .uri("http://httpbin.org:80")

                )
                .route(p-> p.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter) )
                        .uri("lb://security-auth")
                )
                .route(p-> p.path("/api/restaurant/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://restaurant-manager")
                )
                .route(p-> p.path("/api/dish/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://restaurant-manager")
                )
                .route(p-> p.path("/api/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-manager")
                )
                .route(p-> p.path("/api/review/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://review-manager")
                )
                .build();
    }
}
