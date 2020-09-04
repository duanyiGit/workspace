package com.duanyi.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {


    public String reduceProduce(Long productId, Long count) {
        System.out.println("商品productId= "+productId+" 扣减库存:"+count);
        return "success";
    }
}
