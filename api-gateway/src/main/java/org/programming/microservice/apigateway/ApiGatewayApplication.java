package org.programming.microservice.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
      return routeLocatorBuilder.routes()
          .route("auth-service",r->r.path("/api/v1/auth/**")
              .filters(f->f.circuitBreaker(config->config
                  .setName("auth-service-circuit-breaker")
                  .setFallbackUri("forward:/fallback/auth")))
              .uri("lb://auth-service"))

          .route("order-service", r -> r.path("/api/v1/order/**")
              .filters(f -> f.circuitBreaker(config -> config
                  .setName("order-service-circuit-breaker")
                  .setFallbackUri("forward:/fallback/order")))
              .uri("lb://order-service"))

          .route("product-service", r -> r.path("/api/v1/product/**")
              .filters(f -> f.circuitBreaker(config -> config
                  .setName("product-service-circuit-breaker")
                  .setFallbackUri("forward:/fallback/product")))
              .uri("lb://product-service"))

          .route("inventory-service", r -> r.path("/api/inventory/**")
              .filters(f -> f.circuitBreaker(config -> config
                  .setName("inventory-service-circuit-breaker")
                  .setFallbackUri("forward:/fallback/inventory")))
              .uri("lb://inventory-service"))

          .route("cart-service", r -> r.path("/api/v1/cart/items/**")
              .filters(f -> f.circuitBreaker(config -> config
                  .setName("cart-service-circuit-breaker")
                  .setFallbackUri("forward:/fallback/cart")))
              .uri("lb://cart-service"))

          .build();
    }
}