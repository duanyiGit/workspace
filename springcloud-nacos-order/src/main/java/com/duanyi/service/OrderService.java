package com.duanyi.service;

import com.duanyi.feign.CreditService;
import com.duanyi.feign.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    CreditService creditService;

    @Autowired
    ProductService productService;

    public String createOrder() {
        long productId = 2;
        Integer score = 100;
        long count=20;
        creditService.addScore(productId,score);
        productService.reduceProduce(productId,count);
        return "success";
    }
}
