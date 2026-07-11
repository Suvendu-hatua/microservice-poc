package org.programming.microservices.cartservice.feign;

import lombok.extern.slf4j.Slf4j;
import org.programming.microservices.cartservice.exception.ProductServiceUnavailableException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductFeignClientFallbackFactory implements FallbackFactory<ProductFeignClient> {
    @Override
    public ProductFeignClient create(Throwable cause) {
        return productCode -> {
            // Handle the fallback logic here, e.g., return a default response or an error message
          log.error("ProductFeignClient circuit-breaker failed", cause);
          // You can customize this to return a default ProductResponse or an error response
          throw  new ProductServiceUnavailableException("Product service is currently unavailable. Please try again later.", cause);
        };
    }
}
