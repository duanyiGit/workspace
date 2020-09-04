package com.duanyi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("credit-service")
public interface CreditService {

    @GetMapping("/credit/add/{productId}/{score}")
    public String addScore(@PathVariable("productId") long productId,
                           @PathVariable("score") int socre);


}
