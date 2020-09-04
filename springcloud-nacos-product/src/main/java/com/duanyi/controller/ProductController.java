package com.duanyi.controller;

import com.duanyi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/reduce/{productId}/{count}")
    public String reduceProduce(@PathVariable("productId") Long productId,
                                @PathVariable("count") Long count){
        return productService.reduceProduce(productId,count);
    }

}
