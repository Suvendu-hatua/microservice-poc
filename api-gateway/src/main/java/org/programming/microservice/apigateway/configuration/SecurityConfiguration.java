package org.programming.microservice.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(auth -> auth
            .pathMatchers(HttpMethod.POST,
                "/api/v1/auth/login",
                "/api/v1/auth/register"
            ).permitAll()
            .pathMatchers(HttpMethod.GET)
            .permitAll()
            .anyExchange().authenticated());
    return http.build();
  }
}
