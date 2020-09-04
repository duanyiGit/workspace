package com.duanyi.service;

import org.springframework.stereotype.Service;

@Service
public class CreditService {


    public String addScore(long productId, int socre) {
        System.out.println("商品productId= "+productId+" 增加积分:"+socre);
        return "success";
    }
}
