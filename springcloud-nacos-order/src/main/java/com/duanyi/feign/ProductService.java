package com.duanyi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductService {

    @GetMapping("/product/reduce/{productId}/{count}")
    public String reduceProduce(@PathVariable("productId") Long productId,
                                @PathVariable("count") Long count);
}
