package com.programming.microservice.order_service.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class InventoryFeignClientFallbackFactory implements FallbackFactory<InventoryFeignClient> {
    @Override
    public InventoryFeignClient create(Throwable cause) {
        return productCode -> {
            // Handle the fallback logic here, e.g., return an empty list or a default response
            log.error("Feign client error", cause);
            return ResponseEntity.ok(Collections.emptyList());
        };
    }

}
