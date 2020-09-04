package com.duanyi.controller;

import com.duanyi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderService orderService;

    @GetMapping("/creat")
    public String createOrder(){
        return  orderService.createOrder();
    }

    @GetMapping("/product/reduce")
    public String reduceProduct(@RequestParam("productId") Long productId, @RequestParam("count" ) Long count ){
        return restTemplate.getForObject("http://localhost:9001/product/reduce/"+productId+"/"+count,String.class);
    }

    @GetMapping("/credit/add")
    public String addCredit(@RequestParam("productId") Long productId, @RequestParam("score" ) Integer score ){
        return restTemplate.getForObject("http://credit-service/credit/add/"+productId+"/"+score,String.class);
    }

    @RequestMapping("/getKey")
    public String getKey() {
        return restTemplate.postForObject("http://product-center/getKey",null,String.class);
    }



}
