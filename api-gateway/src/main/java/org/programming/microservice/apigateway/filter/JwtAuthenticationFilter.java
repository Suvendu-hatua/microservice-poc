package org.programming.microservice.apigateway.filter;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.programming.microservice.apigateway.util.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

  private final JwtService jwtService;

  public static  final List<String> PUBLIC_ENDPOINTS = List.of(
      "/api/v1/auth/login",
      "/api/v1/auth/register",
      "/api/v1/product/search",
      "/api/v1/product/all"
  );
  /**
   * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
   * through the given {@link GatewayFilterChain}.
   *
   * @param exchange the current server exchange
   * @param chain    provides a way to delegate to the next filter
   * @return {@code Mono<Void>} to indicate when request processing is complete
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String auth = exchange.getRequest()
        .getHeaders()
        .getFirst(HttpHeaders.AUTHORIZATION);
    HttpMethod method = exchange.getRequest().getMethod();
    String path = exchange.getRequest().getURI().getPath();

    if(PUBLIC_ENDPOINTS.contains(path) || HttpMethod.GET.equals(method)) {
      return chain.filter(exchange);
    }

    if(auth==null || !auth.startsWith("Bearer ")){
      exchange.getResponse()
          .setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange
          .getResponse()
          .setComplete();
    }
    String token = auth.substring(7);

    Claims claims = jwtService.validateToken(token);

    ServerHttpRequest request = exchange.getRequest()
        .mutate()
        .header("X-USER-NAME", claims.getSubject())
        .header("X-ROLE", claims.get("roles", List.class).toString())
        .build();
    return chain.filter(
        exchange.mutate().request(request).build()
    );
  }
}
